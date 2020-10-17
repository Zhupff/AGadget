package gadget.base.plugin.asm

import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class BaseGadgetMethodVisitor(mv: MethodVisitor) : MethodVisitor(Opcodes.ASM5, mv)