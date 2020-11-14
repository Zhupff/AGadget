package gadget.common

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
abstract class GConstants private constructor() {
    companion object {

        const val DEF_UNSIGNED_BYTE: Byte = 0
        const val DEF_BYTE: Byte = -73

        const val DEF_UNSIGNED_SHORT: Short = 0
        const val DEF_SHORT: Short = -73

        const val DEF_UNSIGNED_INT: Int = 0
        const val DEF_INT: Int = -73

        const val DEF_UNSIGNED_LONG: Long = 0L
        const val DEF_LONG: Long = -73L

        const val DEF_UNSIGNED_FLOAT: Float = 0.0F
        const val DEF_FLOAT: Float = -73.0F

        const val DEF_UNSIGNED_DOUBLE: Double = 0.0
        const val DEF_DOUBLE: Double = -73.0

        const val DEF_BOOLEAN: Boolean = false

        const val DEF_CHAR: Char = ' '

        const val DEF_STRING: String = ""
        const val DEF_NULL_STRING: String = "null"
        const val DEF_STRING_UNSIGNED_NUM: String = "0"
        const val DEF_STRING_NUM: String = "-73"
    }
}