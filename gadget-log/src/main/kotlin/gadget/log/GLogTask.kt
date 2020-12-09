package gadget.log

import android.util.Log
import gadget.common.g.GTask
import gadget.common.util.GSyncPool

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
internal class GLogTask private constructor() : GTask, Runnable {
    companion object {
        private val TASK_POOL: GSyncPool<GLogTask> = GSyncPool()
        private const val DEF_TAG: String = ""
        private const val EMPTY_MESSAGE = "EMPTY!"

        fun acquireTask(): GLogTask = TASK_POOL.acquire()?.reset() ?: GLogTask()

        fun releaseTask(task: GLogTask): Boolean = TASK_POOL.release(task)
    }

    private var level: GLogLevel = GLogLevel.VERBOSE
    private var tag: String = DEF_TAG
    private var message: String = EMPTY_MESSAGE
    private val content: MutableList<Any> = ArrayList(1)
    private var logFormat: GLogFormat = GSimpleLogFormat

    fun withLevel(level: GLogLevel) = apply { this.level = level }

    fun withTag(tag: String) = apply { this.tag = tag }

    fun withContent(content: Any) = apply { this.content.add(content) }

    fun withContent(content: List<Any>) = apply { this.content.addAll(content) }

    fun withFormatted(formatted: Boolean) = apply { this.logFormat = if (formatted) GNiceLogFormat else GSimpleLogFormat }

    fun reset() = apply {
        this.level = GLogLevel.VERBOSE
        this.tag = DEF_TAG
        this.content.clear()
    }

    override fun ready() {
        if (content.isNotEmpty()) {
            message = logFormat.format(content)
        }
    }

    override fun run() {
        super.run()
        Log.println(level.level, tag, message)
        releaseTask(this)
    }
}