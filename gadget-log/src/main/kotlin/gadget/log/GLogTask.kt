package gadget.log

import gadget.common.util.GSyncPool

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
internal class GLogTask private constructor() {
    companion object {
        private val TASK_POOL: GSyncPool<GLogTask> = GSyncPool()
        private const val DEF_TAG: String = ""

        fun acquireTask(): GLogTask = TASK_POOL.acquire() ?: GLogTask()

        fun releaseTask(task: GLogTask): Boolean = TASK_POOL.release(task.reset())
    }

    var level: GLogLevel = GLogLevel.VERBOSE
    var tag: String = DEF_TAG
    var content: MutableList<Any> = ArrayList(1)

    fun withLevel(level: GLogLevel) = apply { this.level = level }

    fun withTag(tag: String) = apply { this.tag = tag }

    fun withContent(vararg content: Any) = apply { this.content.addAll(content) }

    fun reset() = apply {
        this.level = GLogLevel.VERBOSE
        this.tag = DEF_TAG
        this.content.clear()
    }
}