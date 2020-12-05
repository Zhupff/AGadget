package gadget.log

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
internal interface GLogFormat {
    fun format(content: List<Any>): String
}

object GSimpleLogFormat : GLogFormat {
    override fun format(content: List<Any>): String {
        val sb = StringBuilder()
        content.forEach { sb.append("${it.toString()}\n") }
        return sb.toString()
    }
}

object GNiceLogFormat : GLogFormat {
    override fun format(content: List<Any>): String {
        return GSimpleLogFormat.format(content)
    }
}

object GJsonLogFormat {
//    fun format(prefix: String, content: Any): String {
//
//    }
}