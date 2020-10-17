package gadget.base.common

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
interface Constant {

    val defUnsignedByte: Byte get() = 0
    val defByte: Byte get() = -73

    val defUnsignedShort: Short get() = 0
    val defShort: Short get() = -73

    val defUnsignedInt: Int get() = 0
    val defInt: Int get() = -73

    val defUnsignedLong: Long get() = 0
    val defLong: Long get() = -73

    val defChar: Char get() = '~'

    val defString: String get() = ""
    val defNullString: String get() = "NULL"
    val defStringUnsignedNum: String get() = "0"
    val defStringNum: String get() = "-73"
}