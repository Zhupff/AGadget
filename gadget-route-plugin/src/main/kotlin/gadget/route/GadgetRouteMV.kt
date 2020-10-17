package gadget.route

import gadget.base.plugin.asm.BaseGadgetMethodVisitor
import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
internal class GadgetRouteMV(mv: MethodVisitor, val routeTableList: List<String>)
    : BaseGadgetMethodVisitor(mv) {

    override fun visitCode() {
        mv.visitCode()
        mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList")
        mv.visitInsn(Opcodes.DUP)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false)
        mv.visitVarInsn(Opcodes.ASTORE, 1)

        routeTableList.forEach { routeTable ->
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            mv.visitTypeInsn(Opcodes.NEW, routeTable)
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, routeTable, "<init>", "()V", false)
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z", false)
            mv.visitInsn(Opcodes.POP)
        }

        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(3, 2)
        mv.visitEnd()
    }
}