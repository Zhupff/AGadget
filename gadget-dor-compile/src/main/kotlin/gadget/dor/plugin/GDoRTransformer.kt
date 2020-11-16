package gadget.dor.plugin

import gadget.base.plugin.GPluginContext
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
        const val GDOR_CLASS = "gadget/dor/GDoR"
        const val GDOR_METHOD = "getDoRList"
        const val GDOR_INTERFACE = "gadget/dor/GDoRInterface"
    }

    private val mDoRList: MutableList<String> = ArrayList()

    override fun beforeTransform(context: GPluginContext) {
        super.beforeTransform(context)
        println("GDoRTransformer beforeTransform")
    }

    override fun transformClass(classBytes: ByteArray): ByteArray {
        val cr = ClassReader(classBytes)
        val cn = ClassNode()
        cr.accept(cn, ClassReader.EXPAND_FRAMES)

        if (cn.name == GDOR_CLASS) {
            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            val cv = GDoRCV(cw, mDoRList)
            cr.accept(cv, ClassReader.EXPAND_FRAMES)
            return cw.toByteArray()
        } else if (cn.interfaces.contains(GDOR_INTERFACE)) {
            mDoRList.add(cn.name)
        }

        return super.transformClass(classBytes)
    }

    private class GDoRCV(cv: ClassVisitor, val mDoRList: List<String>)
        : GClassVisitor(cv) {

        override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<String>?): MethodVisitor {
            val mv = super.visitMethod(access, name, desc, signature, exceptions)
            return if (name == GDOR_METHOD) GDoRMV(mv, mDoRList) else mv
        }
    }


    private class GDoRMV(mv: MethodVisitor, val mDoRList: List<String>) : GMethodVisitor(mv) {

        override fun visitCode() {
            mv.visitCode()
            mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList")
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)

            mDoRList.forEach { dor ->
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