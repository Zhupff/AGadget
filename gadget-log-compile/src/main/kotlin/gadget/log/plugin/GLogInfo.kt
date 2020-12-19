package gadget.log.plugin

import gadget.common.util.GList
import gadget.common.util.GString
import gadget.common.util.GSyncPool
import gadget.log.GLogConstants.Companion.GDLOG_ANNOTATIONS
import gadget.log.GLogConstants.Companion.GDLOG_D_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GDLOG_E_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GDLOG_I_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GDLOG_V_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GDLOG_W_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GLOG_ANNOTATIONS
import gadget.log.GLogConstants.Companion.GLOG_D_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GLOG_D_METHOD_NAME
import gadget.log.GLogConstants.Companion.GLOG_E_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GLOG_E_METHOD_NAME
import gadget.log.GLogConstants.Companion.GLOG_I_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GLOG_I_METHOD_NAME
import gadget.log.GLogConstants.Companion.GLOG_V_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GLOG_V_METHOD_NAME
import gadget.log.GLogConstants.Companion.GLOG_W_ANNOTATION_DESC
import gadget.log.GLogConstants.Companion.GLOG_W_METHOD_NAME
import gadget.log.GLogOption
import gadget.log.GLogOption.*
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
        fun acquire(cn: ClassNode, mn: MethodNode): GLogInfo = (GLOGINFO_POOL.acquire() ?: GLogInfo()).init(cn, mn)
        fun release(logInfo: GLogInfo): Boolean = GLOGINFO_POOL.release(logInfo)
    }

    var cnSimpleName: String = ""
    var isInnerClass: Boolean = false
    var isStaticMethod: Boolean = false

    var hasLogAnnotation: Boolean = false
    var logClass: String = ""
    var logMethod: String = ""
    var logTag: String = ""
    var logOption: MutableSet<GLogOption> = HashSet()

    private fun init(cn: ClassNode, mn: MethodNode) = apply {
        cnSimpleName = GString.subStringAfterLast(cn.name, "/", 1, cn.name)
        isInnerClass = cnSimpleName.contains("$")
        isStaticMethod = (mn.access and Opcodes.ACC_STATIC) != 0

        hasLogAnnotation = false
        mn.invisibleAnnotations?.forEach { an ->
            if (GLOG_ANNOTATIONS.contains(an.desc)) {
                logClass = GLogTransformer.GLOG_CLASS_NAME
                logMethod = when (an.desc) {
                    GLOG_V_ANNOTATION_DESC -> GLOG_V_METHOD_NAME
                    GLOG_D_ANNOTATION_DESC -> GLOG_D_METHOD_NAME
                    GLOG_I_ANNOTATION_DESC -> GLOG_I_METHOD_NAME
                    GLOG_W_ANNOTATION_DESC -> GLOG_W_METHOD_NAME
                    GLOG_E_ANNOTATION_DESC -> GLOG_E_METHOD_NAME
                    else -> GLOG_V_METHOD_NAME
                }
            } else if (GDLOG_ANNOTATIONS.contains(an.desc)) {
                logClass = GLogTransformer.GDLOG_CLASS_NAME
                logMethod = when (an.desc) {
                    GDLOG_V_ANNOTATION_DESC -> GLOG_V_METHOD_NAME
                    GDLOG_D_ANNOTATION_DESC -> GLOG_D_METHOD_NAME
                    GDLOG_I_ANNOTATION_DESC -> GLOG_I_METHOD_NAME
                    GDLOG_W_ANNOTATION_DESC -> GLOG_W_METHOD_NAME
                    GDLOG_E_ANNOTATION_DESC -> GLOG_E_METHOD_NAME
                    else -> GLOG_V_METHOD_NAME
                }
            } else {
                return@forEach
            }
            hasLogAnnotation = true
            logOption.clear()

            val kv = GList.toMapWithStringKey(an.values)
            logTag = kv["tag"] as? String ?: "${cnSimpleName}-${mn.name}"
            (kv["option"] as? List<*>)?.filterIsInstance<Array<*>>()?.forEach { option ->
                when (option.last().toString()) {
                    PARAMETER_VALUE.name -> logOption.add(PARAMETER_VALUE)
                    RETURN_VALUE.name -> logOption.add(RETURN_VALUE)
                    DURATION.name -> logOption.add(DURATION)
                }
            }
        }
    }
}