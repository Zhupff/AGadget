package gadget.base.plugin.asm

import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object GOpcodes {

    fun getInsnCode(value: Int): Int = when(value) {
        -1 -> Opcodes.ICONST_M1
        0 -> Opcodes.ICONST_0
        1 -> Opcodes.ICONST_1
        2 -> Opcodes.ICONST_2
        3 -> Opcodes.ICONST_3
        4 -> Opcodes.ICONST_4
        5 -> Opcodes.ICONST_5
        in -128..127 -> Opcodes.BIPUSH
        in -32768..32767 -> Opcodes.SIPUSH
        else -> Opcodes.LDC
    }
}