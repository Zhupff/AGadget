package gadget.route

import gadget.base.plugin.asm.BaseGadgetClassVisitor
import jdk.internal.org.objectweb.asm.ClassVisitor
import jdk.internal.org.objectweb.asm.MethodVisitor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
internal class GadgetRouteCV(cv: ClassVisitor, val routeTableList: List<String>)
    : BaseGadgetClassVisitor(cv) {

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<String>?): MethodVisitor {
        val mv = super.visitMethod(access, name, desc, signature, exceptions)
        return if (GadgetRouteConstant._getTaG_Route == name) GadgetRouteMV(mv, routeTableList) else mv
    }
}