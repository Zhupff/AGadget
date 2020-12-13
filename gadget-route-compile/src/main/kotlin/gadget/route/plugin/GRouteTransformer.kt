package gadget.route.plugin

import gadget.base.plugin.asm.GClassVisitor
import gadget.base.plugin.asm.GMethodVisitor
import gadget.base.plugin.asm.GTransformer
import gadget.route.GRouteConstants
import jdk.internal.org.objectweb.asm.*
import jdk.internal.org.objectweb.asm.tree.ClassNode

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class GRouteTransformer : GTransformer() {
    companion object {
        private const val GROUTER_CLASS_NAME = "gadget/route/GRouter"
        private const val GROUTER_METHOD_NAME = "getGRouteTables"
        private const val GROUTETABLE_INTERFACE_NAME = "gadget/route/GRouteTable"
        private const val GROUTETABLE_CLASS = "${GRouteConstants.GROUTETABLE_SUFFIX}.class"
    }

    private val routeTableList: MutableList<String> = ArrayList()

    override fun filterClass(className: String): Boolean {
        return className.startsWith(GROUTER_CLASS_NAME) || className.endsWith(GROUTETABLE_CLASS)
    }

    override fun transformClass(classBytes: ByteArray): ByteArray {
        val cr = ClassReader(classBytes)
        val cn = ClassNode()
        cr.accept(cn, ClassReader.EXPAND_FRAMES)

        if (cn.name == GROUTER_CLASS_NAME) {
            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
            val cv = GRouteCV(cw, routeTableList)
            cr.accept(cv, ClassReader.EXPAND_FRAMES)
            return cw.toByteArray()
        } else if (cn.interfaces.contains(GROUTETABLE_INTERFACE_NAME)) {
            routeTableList.add(cn.name)
        }

        return super.transformClass(classBytes)
    }


    private class GRouteCV(cv: ClassVisitor, val routeTableList: List<String>) : GClassVisitor(cv) {
        override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<String>?): MethodVisitor {
            val mv = super.visitMethod(access, name, desc, signature, exceptions)
            return if (name == GROUTER_METHOD_NAME) GRouteMV(mv, routeTableList) else mv
        }
    }


    private class GRouteMV(mv: MethodVisitor, val routeTableList: List<String>) : GMethodVisitor(mv) {
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
}