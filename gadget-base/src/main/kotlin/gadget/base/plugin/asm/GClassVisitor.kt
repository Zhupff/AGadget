package gadget.base.plugin.asm

import gadget.common.GConstants
import jdk.internal.org.objectweb.asm.ClassVisitor
import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GClassVisitor(cv: ClassVisitor) : ClassVisitor(Opcodes.ASM5, cv) {

    protected var mClassVersion: Int = GConstants.DEF_INT
    protected var mClassAccess: Int = GConstants.DEF_INT
    protected var mClassName: String? = GConstants.DEF_STRING
    protected var mClassSignature: String? = GConstants.DEF_STRING
    protected var mClassSuperName: String? = GConstants.DEF_STRING
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