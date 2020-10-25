package gadget.dor

import gadget.base.plugin.asm.BaseGadgetClassVisitor
import jdk.internal.org.objectweb.asm.ClassVisitor
import jdk.internal.org.objectweb.asm.MethodVisitor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
internal class GadgetDoRCV(cv: ClassVisitor, val mDorList: List<String>)
    : BaseGadgetClassVisitor(cv), GadgetDoRConstant {

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<String>?): MethodVisitor {
        val mv = super.visitMethod(access, name, desc, signature, exceptions)
        return if (_getTaG_DoR == name) GadgetDoRMV(mv, mDorList) else mv
    }
}