package gadget.base.plugin.asm

import gadget.base.common.Constant
import jdk.internal.org.objectweb.asm.ClassVisitor
import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class BaseGadgetClassVisitor(cv: ClassVisitor) : ClassVisitor(Opcodes.ASM5, cv), Constant {

    protected var mClassVersion: Int = defInt
    protected var mClassAccess: Int = defInt
    protected var mClassName: String? = defString
    protected var mClassSignature: String? = defString
    protected var mClassSuperName: String? = defString
    protected var mClassInterfaces: Array<String>? = emptyArray()

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        mClassVersion = version
        mClassAccess = access
        mClassName = name
        mClassSignature = signature
        mClassSuperName = superName
        mClassInterfaces = interfaces
    }
}