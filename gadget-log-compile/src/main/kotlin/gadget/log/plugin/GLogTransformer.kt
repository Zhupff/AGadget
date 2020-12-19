package gadget.log.plugin

import gadget.base.plugin.GPluginContext
import gadget.base.plugin.asm.GTransformer
import gadget.common.util.GString
import gadget.log.GLogConstants.Companion.GLOG_METHODS
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
        const val GLOG_CLASS_NAME = "gadget/log/GLog"
        const val GDLOG_CLASS_NAME = "gadget/log/GDLog"
        private const val TRANSFORM_METHOD_NAME = "log"
        private const val TRANSFORM_METHOD_DESC = "(Lgadget/log/GLogTask;Ljava/lang/String;)Lgadget/log/GLogTask;"
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
            val logInfo = GLogInfo.acquire(cn, mn)

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
        if (!logInfo.hasLogAnnotation) {
            return false
        }
        return true
    }

    private fun transformMethodWhenLeave(logInfo: GLogInfo, mn: MethodNode): Boolean {
        if (!logInfo.hasLogAnnotation) {
            return false
        }
        return true
    }
}