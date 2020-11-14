package gadget.base.plugin.asm

import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Opcodes
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GAdviceAdapter(mv: MethodVisitor, access: Int, name: String, desc: String)
    : AdviceAdapter(Opcodes.ASM5, mv, access, name, desc)