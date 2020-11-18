package gadget.base.plugin.asm

import gadget.common.GConstants
import jdk.internal.org.objectweb.asm.ClassVisitor
import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GClassVisitor(cv: ClassVisitor) : ClassVisitor(Opcodes.ASM5, cv) {

    protected var classVersion: Int = GConstants.DEF_INT
    protected var classAccess: Int = GConstants.DEF_INT
    protected var className: String? = GConstants.DEF_STRING
    protected var classSignature: String? = GConstants.DEF_STRING
    protected var classSuperName: String? = GConstants.DEF_STRING
    protected var classInterfaces: Array<String>? = emptyArray()

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        this.classVersion = version
        this.classAccess = access
        this.className = name
        this.classSignature = signature
        this.classSuperName = superName
        this.classInterfaces = interfaces
    }
}