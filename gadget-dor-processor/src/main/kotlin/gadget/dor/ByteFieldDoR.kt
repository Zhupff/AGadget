package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class ByteFieldDoR(override val name: String, override val debug: Byte, override val release: Byte)
    : FieldDoR<Byte>