package gadget.log.plugin

import gadget.common.util.GList
import gadget.common.util.GString
import gadget.common.util.GSyncPool
import gadget.log.GLogOption
import jdk.internal.org.objectweb.asm.Opcodes
import jdk.internal.org.objectweb.asm.tree.ClassNode
import jdk.internal.org.objectweb.asm.tree.MethodNode

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
class GLogInfo private constructor() {
    companion object {
        private val GLOGINFO_POOL: GSyncPool<GLogInfo> = GSyncPool()
        fun acquire(): GLogInfo = GLOGINFO_POOL.acquire()?.reset() ?: GLogInfo()
        fun release(logInfo: GLogInfo): Boolean = GLOGINFO_POOL.release(logInfo)
    }

    var cnSimpleName: String = ""
    var isInnerClass: Boolean = false
    var isStaticMethod: Boolean = false

    var logMethod: String = ""
    var logTag: String = ""
    var logOption: MutableSet<GLogOption> = HashSet()

    fun init(cn: ClassNode, mn: MethodNode) = apply {
        cnSimpleName = GString.subStringAfterLast(cn.name, "/", 1, cn.name)
        isInnerClass = cnSimpleName.contains("$")
        isStaticMethod = (mn.access and Opcodes.ACC_STATIC) != 0

        mn.invisibleAnnotations?.forEach { an ->
            if (GLogTransformer.GLOG_ANNOTATIONS.contains(an.desc)) {
                logMethod = when (an.desc) {
                    GLogTransformer.GLOG_V_ANNOTATION_DESC -> GLogTransformer.GLOG_V_METHOD_NAME
                    GLogTransformer.GLOG_D_ANNOTATION_DESC -> GLogTransformer.GLOG_D_METHOD_NAME
                    GLogTransformer.GLOG_I_ANNOTATION_DESC -> GLogTransformer.GLOG_I_METHOD_NAME
                    GLogTransformer.GLOG_W_ANNOTATION_DESC -> GLogTransformer.GLOG_W_METHOD_NAME
                    GLogTransformer.GLOG_E_ANNOTATION_DESC -> GLogTransformer.GLOG_E_METHOD_NAME
                    else -> "" // should not reach here.
                }

                an.values?.let { values ->
                    val kv = GList.toMapWithStringKey(values)

                    // reset log tag.
                    logTag = "${cnSimpleName}-${mn.name}"
                    val tag = kv["tag"] as? String
                    if (GString.isNotNullNorEmpty(tag)) {
                        logTag = tag!!
                    }

                    // reset log options.
                    logOption.clear()
                    val options = kv["option"] as? List<*>
                    options?.forEach { option ->
                        if (option is Array<*>) {
                            when (option.last().toString()) {
                                GLogOption.PARAMETER_VALUE.name -> logOption.add(GLogOption.PARAMETER_VALUE)
                                GLogOption.RETURN_VALUE.name -> logOption.add(GLogOption.RETURN_VALUE)
                                GLogOption.DURATION.name -> logOption.add(GLogOption.DURATION)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun reset() = apply {
        cnSimpleName = ""
        isInnerClass = false
        isStaticMethod = false
        logMethod = ""
        logTag = ""
        logOption.clear()
    }
}