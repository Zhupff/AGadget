package gadget.api.plugin.asm

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter
import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.AnnotationNode
import org.objectweb.asm.tree.InsnNode
import org.objectweb.asm.tree.IntInsnNode

/**
 * @Author: Zhupf
 */
abstract class GadgetClassVisitor(classVisitor: ClassVisitor)
    : ClassVisitor(Opcodes.ASM5, classVisitor) {

    var classVersion: Int = 0
        protected set
    var classAccess: Int = 0
        protected set
    var className: String = ""
        protected set
    var classSignature: String? = null
        protected set
    var classSuperName: String? = null
        protected set
    var classInterfaces: Array<String>? = null
        protected set

    override fun visit(version: Int, access: Int, name: String, signature: String?, superName: String?, interfaces: Array<String>?) {
        this.classVersion = version
        this.classAccess = access
        this.className = name
        this.classSignature = signature
        this.classSuperName = superName
        this.classInterfaces = interfaces
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(access: Int, name: String, desc: String, signature: String?, exceptions: Array<String>?): MethodVisitor {
        return super.visitMethod(access, name, desc, signature, exceptions)
    }
}

abstract class GadgetMethodVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor)

abstract class GadgetAdviceAdapter(mv: MethodVisitor, access: Int, name: String, desc: String)
    : AdviceAdapter(Opcodes.ASM5, mv, access, name, desc)


fun AnnotationNode.getValueMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    if (values != null && values.isNotEmpty()) {
        for (i in values.indices step 2) {
            if (i + 1 < values.size) {
                map[values[i].toString()] = values[i + 1]
            }
        }
    }
    return map
}

fun AbstractInsnNode.isReturnNode(): Boolean = opcode in arrayOf(
    Opcodes.RETURN, Opcodes.ARETURN, Opcodes.DRETURN, Opcodes.FRETURN, Opcodes.IRETURN, Opcodes.LRETURN)

fun AbstractInsnNode.newInsnNode(i: Int): InsnNode = when (i) {
    -1 -> InsnNode(Opcodes.ICONST_M1)
    0 -> InsnNode(Opcodes.ICONST_0)
    1 -> InsnNode(Opcodes.ICONST_1)
    2 -> InsnNode(Opcodes.ICONST_2)
    3 -> InsnNode(Opcodes.ICONST_3)
    4 -> InsnNode(Opcodes.ICONST_4)
    5 -> InsnNode(Opcodes.ICONST_5)
    else -> throw IllegalArgumentException(
        "Cannot invoke getInsnNode(${i}), Consider use getIntInsnNode(${i}) instead.")
}

fun AbstractInsnNode.newIntInsnNode(i: Int): IntInsnNode = when (i) {
    in -1..5 -> throw IllegalArgumentException(
        "Cannot invoke getIntInsnNode(${i}), Consider use getInsnNode(${i}) instead.")
    in -128..127 -> IntInsnNode(Opcodes.BIPUSH, i)
    in - 32768..32767 -> IntInsnNode(Opcodes.SIPUSH, i)
    else -> IntInsnNode(Opcodes.LDC, i)
}