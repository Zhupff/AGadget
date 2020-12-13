package gadget.log.plugin

import gadget.base.plugin.GPluginContext
import gadget.base.plugin.asm.GTransformer
import gadget.common.util.GString
import jdk.internal.org.objectweb.asm.ClassReader
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.Opcodes
import jdk.internal.org.objectweb.asm.tree.*

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class GLogTransformer : GTransformer() {
    companion object {
        private const val GLOG_CLASS_NAME = "gadget/log/GLog"
        private const val GDLOG_CLASS_NAME = "gadget/log/GDLog"
        private const val TRANSFORM_METHOD_NAME = "log"
        private const val TRANSFORM_METHOD_DESC = "(Lgadget/log/GLogTask;Ljava/lang/String;)Lgadget/log/GLogTask;"

        internal const val GLOG_V_METHOD_NAME = "V"
        internal const val GLOG_D_METHOD_NAME = "D"
        internal const val GLOG_I_METHOD_NAME = "I"
        internal const val GLOG_W_METHOD_NAME = "W"
        internal const val GLOG_E_METHOD_NAME = "E"
        private const val GLOG_VV_METHOD_NAME = "VV"
        private const val GLOG_DD_METHOD_NAME = "DD"
        private const val GLOG_II_METHOD_NAME = "II"
        private const val GLOG_WW_METHOD_NAME = "WW"
        private const val GLOG_EE_METHOD_NAME = "EE"
        internal val GLOG_METHODS = arrayOf(
            GLOG_V_METHOD_NAME, GLOG_D_METHOD_NAME, GLOG_I_METHOD_NAME, GLOG_W_METHOD_NAME, GLOG_E_METHOD_NAME,
            GLOG_VV_METHOD_NAME, GLOG_DD_METHOD_NAME, GLOG_II_METHOD_NAME, GLOG_WW_METHOD_NAME, GLOG_EE_METHOD_NAME
        )

        internal const val GLOG_V_ANNOTATION_DESC = "Lgadget/log/GLogI;"
        internal const val GLOG_D_ANNOTATION_DESC = "Lgadget/log/GLogD;"
        internal const val GLOG_I_ANNOTATION_DESC = "Lgadget/log/GLogI;"
        internal const val GLOG_W_ANNOTATION_DESC = "Lgadget/log/GLogW;"
        internal const val GLOG_E_ANNOTATION_DESC = "Lgadget/log/GLogE;"
        internal val GLOG_ANNOTATIONS = arrayOf(GLOG_V_ANNOTATION_DESC, GLOG_D_ANNOTATION_DESC,
            GLOG_I_ANNOTATION_DESC, GLOG_W_ANNOTATION_DESC, GLOG_E_ANNOTATION_DESC)
    }

    private var isDebug: Boolean = false

    override fun beforeTransform(context: GPluginContext) {
        super.beforeTransform(context)
        isDebug = context.debug()
    }

    override fun filterJarClass(className: String): Boolean {
        return className.startsWith(GLOG_CLASS_NAME) || className.startsWith(GDLOG_CLASS_NAME)
    }

    override fun transformJarClass(classBytes: ByteArray): ByteArray {
        val cr = ClassReader(classBytes)
        val cn = ClassNode()
        cr.accept(cn, ClassReader.EXPAND_FRAMES)

        if (cn.name == GLOG_CLASS_NAME || cn.name == GDLOG_CLASS_NAME) {
            cn.methods.forEach { mn ->
                if (mn.name == TRANSFORM_METHOD_NAME && mn.desc == TRANSFORM_METHOD_DESC) {
                    mn.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC
                }
            }
            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            cn.accept(cw)
            return cw.toByteArray()
        }
        return super.transformJarClass(classBytes)
    }

    override fun transformDirClass(classBytes: ByteArray): ByteArray {
        val cr = ClassReader(classBytes)
        val cn = ClassNode()
        cr.accept(cn, ClassReader.EXPAND_FRAMES)

        var hasTransformed = false
        val cnSimpleName = GString.subStringAfterLast(cn.name, "/", 1, cn.name)
        cn.methods.forEach { mn ->
            val logInfo = GLogInfo.acquire().init(cn, mn)

            hasTransformed = hasTransformed || transformMethodWhenEnter(logInfo, mn)

            val instructions = mn.instructions
            var line = 0
            instructions.iterator().forEach { ain ->
                if (ain is LineNumberNode) {
                    line = ain.line
                } else if (ain is MethodInsnNode) {
                    if ((ain.owner == GLOG_CLASS_NAME || ain.owner == GDLOG_CLASS_NAME) && GLOG_METHODS.contains(ain.name)) {
                        if (logInfo.isInnerClass || logInfo.isStaticMethod) {
                            val insnList = InsnList()
                            insnList.add(LdcInsnNode("${cnSimpleName}-${mn.name}(${line})"))
                            insnList.add(MethodInsnNode(Opcodes.INVOKESTATIC, ain.owner, TRANSFORM_METHOD_NAME, TRANSFORM_METHOD_DESC, false))
                            instructions.insert(ain, insnList)
                        } else {
                            val insnList = InsnList()
                            insnList.add(TypeInsnNode(Opcodes.NEW, "java/lang/StringBuilder"))
                            insnList.add(InsnNode(Opcodes.DUP))
                            insnList.add(MethodInsnNode(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false))
                            insnList.add(VarInsnNode(Opcodes.ALOAD, 0))
                            insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false))
                            insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false))
                            insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false))
                            insnList.add(LdcInsnNode("-${mn.name}(${line})"))
                            insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false))
                            insnList.add(MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false))
                            insnList.add(MethodInsnNode(Opcodes.INVOKESTATIC, ain.owner, TRANSFORM_METHOD_NAME, TRANSFORM_METHOD_DESC, false))
                            instructions.insert(ain, insnList)
                        }
                        hasTransformed = true
                    }
                }
            }

            hasTransformed = hasTransformed || transformMethodWhenLeave(logInfo, mn)
            GLogInfo.release(logInfo)
        }

        if (hasTransformed) {
            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            cn.accept(cw)
            return cw.toByteArray()
        }

        return super.transformDirClass(classBytes)
    }

    private fun transformMethodWhenEnter(logInfo: GLogInfo, mn: MethodNode): Boolean {
        if (GString.isNullOrEmpty(logInfo.logMethod)) {
            return false
        }
        return true
    }

    private fun transformMethodWhenLeave(logInfo: GLogInfo, mn: MethodNode): Boolean {
        if (GString.isNullOrEmpty(logInfo.logMethod)) {
            return false
        }
        return true
    }
}