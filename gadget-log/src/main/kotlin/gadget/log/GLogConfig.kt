package gadget.log

import gadget.common.model.GLock

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
class GLogConfig private constructor() {
    companion object {
        fun newDefGLogConfig(): GLogConfig {
            return GLogConfig()
                .withDefTag("GLogTag")
                .workMode(debug = true, release = true)
                .withAllFormatted(false)
        }

        fun newDefGDLogConfig(): GLogConfig {
            return GLogConfig()
                .withDefTag("GDLogTag")
                .workMode(debug = true, release = false)
                .withAllFormatted(true)
        }
    }

    private val lock: GLock = GLock("GLogConfig")
    private var workInDebug: Boolean = true
    private var workInRelease: Boolean = false
    private fun workMode(debug: Boolean, release: Boolean) = apply {
        this.workInDebug = debug
        this.workInRelease = release
    }
    internal fun needWork() = if (debug) workInDebug else workInRelease
    internal fun needFormatted(level: GLogLevel): Boolean = when (level) {
        GLogLevel.VERBOSE -> vFormatted
        GLogLevel.DEBUG -> dFormatted
        GLogLevel.INFO -> iFormatted
        GLogLevel.WARN -> wFormatted
        GLogLevel.ERROR -> eFormatted
    }


    private var debug: Boolean = false
    private var defTag: String = "GLogTag"
    private var vFormatted: Boolean = false
    private var dFormatted: Boolean = false
    private var iFormatted: Boolean = false
    private var wFormatted: Boolean = false
    private var eFormatted: Boolean = false

    fun withDebug(debug: Boolean) = synchronized(lock) { apply { this.debug = debug } }
    fun withDefTag(tag: String) = synchronized(lock) { apply { this.defTag = tag } }
    fun withVFormatted(formatted: Boolean) = synchronized(lock) { apply { this.vFormatted = formatted } }
    fun withDFormatted(formatted: Boolean) = synchronized(lock) { apply { this.dFormatted = formatted } }
    fun withIFormatted(formatted: Boolean) = synchronized(lock) { apply { this.iFormatted = formatted } }
    fun withWFormatted(formatted: Boolean) = synchronized(lock) { apply { this.wFormatted = formatted } }
    fun withEFormatted(formatted: Boolean) = synchronized(lock) { apply { this.eFormatted = formatted } }
    fun withAllFormatted(formatted: Boolean) = synchronized(lock) {
        apply {
            this.vFormatted = formatted
            this.dFormatted = formatted
            this.iFormatted = formatted
            this.wFormatted = formatted
            this.eFormatted = formatted
        }
    }

    fun getDefTag(): String = defTag
}