package gadget.common

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
class G_Constant private constructor() {
    companion object {

        const val defUnsignedByte: Byte = 0
        const val defByte: Byte = -73

        const val defUnsignedShort: Short = 0
        const val defShort: Short = -73

        const val defUnsignedInt: Int = 0
        const val defInt: Int = -73

        const val defUnsignedLong: Long = 0L
        const val defLong: Long = -73L

        const val defChar: Char = ' '

        const val defString: String = ""
        const val defNullString: String = "NULL"
        const val defStringUnsignedNum: String = "0"
        const val defStringNum: String = "-73"
    }
}