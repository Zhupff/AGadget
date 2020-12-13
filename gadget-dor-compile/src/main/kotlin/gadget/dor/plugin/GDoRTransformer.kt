package gadget.dor.plugin

import gadget.base.plugin.asm.GClassVisitor
import gadget.base.plugin.asm.GMethodVisitor
import gadget.base.plugin.asm.GTransformer
import jdk.internal.org.objectweb.asm.*
import jdk.internal.org.objectweb.asm.tree.ClassNode

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class GDoRTransformer : GTransformer() {
    companion object {
        private const val GDOR_CLASS = "gadget/dor/GDoR"
        private const val GDOR_METHOD = "getGDoRTables"
        private const val GDORTABLE_INTERFACE = "gadget/dor/GDoRTable"
    }

    private val dorList: MutableList<String> = ArrayList()

    override fun transformClass(classBytes: ByteArray): ByteArray {
        val cr = ClassReader(classBytes)
        val cn = ClassNode()
        cr.accept(cn, ClassReader.EXPAND_FRAMES)

        if (cn.name == GDOR_CLASS) {
            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            val cv = GDoRCV(cw, dorList)
            cr.accept(cv, ClassReader.EXPAND_FRAMES)
            return cw.toByteArray()
        } else if (cn.interfaces.contains(GDORTABLE_INTERFACE)) {
            dorList.add(cn.name)
        }

        return super.transformClass(classBytes)
    }

    private class GDoRCV(cv: ClassVisitor, val dorList: List<String>)
        : GClassVisitor(cv) {

        override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<String>?): MethodVisitor {
            val mv = super.visitMethod(access, name, desc, signature, exceptions)
            return if (name == GDOR_METHOD) GDoRMV(mv, dorList) else mv
        }
    }


    private class GDoRMV(mv: MethodVisitor, val dorList: List<String>) : GMethodVisitor(mv) {

        override fun visitCode() {
            mv.visitCode()
            mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList")
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)

            dorList.forEach { dor ->
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                mv.visitTypeInsn(Opcodes.NEW, dor)
                mv.visitInsn(Opcodes.DUP)
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, dor, "<init>", "()V", false)
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z", false)
                mv.visitInsn(Opcodes.POP)
            }

            mv.visitVarInsn(Opcodes.ALOAD, 1)
            mv.visitInsn(Opcodes.ARETURN)
            mv.visitMaxs(3, 2)
            mv.visitEnd()
        }
    }
}