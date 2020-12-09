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
        private const val GLOG_PACKAGE = "gadget/log/"
        private const val GLOG_CLASS_NAME = "${GLOG_PACKAGE}GLog"
        private const val GDLOG_CLASS_NAME = "${GLOG_PACKAGE}GDLog"
        private const val TRANSFORM_METHOD_NAME = "log"
        private const val TRANSFORM_METHOD_DESC = "(Lgadget/log/GLogTask;Ljava/lang/String;)Lgadget/log/GLogTask;"
        private const val LOG_V_METHOD_NAME = "V"
        private const val LOG_D_METHOD_NAME = "D"
        private const val LOG_I_METHOD_NAME = "I"
        private const val LOG_W_METHOD_NAME = "W"
        private const val LOG_E_METHOD_NAME = "E"
        private const val LOG_VV_METHOD_NAME = "VV"
        private const val LOG_DD_METHOD_NAME = "DD"
        private const val LOG_II_METHOD_NAME = "II"
        private const val LOG_WW_METHOD_NAME = "WW"
        private const val LOG_EE_METHOD_NAME = "EE"
        private val LOG_METHODS = arrayOf(
            LOG_V_METHOD_NAME, LOG_D_METHOD_NAME, LOG_I_METHOD_NAME, LOG_W_METHOD_NAME, LOG_E_METHOD_NAME,
            LOG_VV_METHOD_NAME, LOG_DD_METHOD_NAME, LOG_II_METHOD_NAME, LOG_WW_METHOD_NAME, LOG_EE_METHOD_NAME
        )
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
            val instructions = mn.instructions
            var line = 0
            instructions.iterator().forEach { ain ->
                if (ain is LineNumberNode) {
                    line = ain.line
                } else if (ain is MethodInsnNode) {
                    if ((ain.owner == GLOG_CLASS_NAME && LOG_METHODS.contains(ain.name)) ||
                        (isDebug && ain.owner == GDLOG_CLASS_NAME && LOG_METHODS.contains(ain.name))) {
                        if ((mn.access and Opcodes.ACC_STATIC != 0) || // static method
                            cnSimpleName.contains("$")) { // inner class
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
        }

        if (hasTransformed) {
            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            cn.accept(cw)
            return cw.toByteArray()
        }

        return super.transformDirClass(classBytes)
    }
}