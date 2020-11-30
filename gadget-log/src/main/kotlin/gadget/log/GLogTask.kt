package gadget.log

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
internal class GLogTask private constructor() {
    companion object {
        fun newTask(): GLogTask = GLogTask()
    }

    var level: GLogLevel = GLogLevel.VERBOSE
    var tag: String = ""
    var content: MutableList<Any> = ArrayList(1)

    fun withLevel(level: GLogLevel) = apply { this.level = level }

    fun withTag(tag: String) = apply { this.tag = tag }

    fun withContent(vararg content: Any) = apply { this.content.addAll(content) }
}