package gadget.api.common

import gadget.api.closure.GadgetLoggerClosure

/**
 * @Author: Zhupf
 */
object Logger {

    private enum class Level {
        VERBOSE, DEBUG, INFO, WARN, ERROR
    }

    private var minLevel: Level = Level.VERBOSE
    private var maxLevel: Level = Level.ERROR

    fun init(config: GadgetLoggerClosure?) {
        config?.let {
            minLevel = when (it.minLevel?.toLowerCase()) {
                "e", "error" -> Level.ERROR
                "w", "warn" -> Level.WARN
                "i", "info" -> Level.INFO
                "d", "debug" -> Level.DEBUG
                "v", "verbose" -> Level.VERBOSE
                else -> Level.VERBOSE
            }
            maxLevel = when (it.maxLevel?.toLowerCase()) {
                "v", "verbose" -> Level.VERBOSE
                "d", "debug" -> Level.DEBUG
                "i", "info" -> Level.INFO
                "w", "warn" -> Level.WARN
                "e", "error" -> Level.ERROR
                else -> Level.ERROR
            }
        }
    }

    internal fun v(msg: Any, ln: Boolean) {
        log(Level.VERBOSE, msg, ln)
    }

    internal fun d(msg: Any, ln: Boolean) {
        log(Level.DEBUG, msg, ln)
    }

    internal fun i(msg: Any, ln: Boolean) {
        log(Level.INFO, msg, ln)
    }

    internal fun w(msg: Any, ln: Boolean) {
        log(Level.WARN, msg, ln)
    }

    internal fun e(msg: Any, ln: Boolean) {
        log(Level.ERROR, msg, ln)
    }

    internal fun min(msg: Any, ln: Boolean) {
        log(minLevel, msg, ln)
    }

    internal fun max(msg: Any, ln: Boolean) {
        log(maxLevel, msg, ln)
    }

    private fun log(level: Level, msg: Any, ln: Boolean) {
        if (level < minLevel || level > maxLevel) {
            return
        }
        if (ln) {
            println(msg)
        } else {
            print(msg)
        }
    }
}

object Log {

    @JvmStatic
    fun v(msg: String, vararg args: Any?) {
        Logger.v(String.format(msg, *args), false)
    }

    @JvmStatic
    fun d(msg: String, vararg args: Any?) {
        Logger.d(String.format(msg, *args), false)
    }

    @JvmStatic
    fun i(msg: String, vararg args: Any?) {
        Logger.i(String.format(msg, *args), false)
    }

    @JvmStatic
    fun w(msg: String, vararg args: Any?) {
        Logger.w(String.format(msg, *args), false)
    }

    @JvmStatic
    fun e(msg: String, vararg args: Any?) {
        Logger.e(String.format(msg, *args), false)
    }

    @JvmStatic
    fun min(msg: String, vararg args: Any?) {
        Logger.min(String.format(msg, *args), false)
    }

    @JvmStatic
    fun max(msg: String, vararg args: Any?) {
        Logger.max(String.format(msg, *args), false)
    }
}

object Logln {

    @JvmStatic
    fun v(msg: String, vararg args: Any?) {
        Logger.v(String.format(msg, *args), true)
    }

    @JvmStatic
    fun d(msg: String, vararg args: Any?) {
        Logger.d(String.format(msg, *args), true)
    }

    @JvmStatic
    fun i(msg: String, vararg args: Any?) {
        Logger.i(String.format(msg, *args), true)
    }

    @JvmStatic
    fun w(msg: String, vararg args: Any?) {
        Logger.w(String.format(msg, *args), true)
    }

    @JvmStatic
    fun e(msg: String, vararg args: Any?) {
        Logger.e(String.format(msg, *args), true)
    }

    @JvmStatic
    fun min(msg: String, vararg args: Any?) {
        Logger.min(String.format(msg, *args), true)
    }

    @JvmStatic
    fun max(msg: String, vararg args: Any?) {
        Logger.max(String.format(msg, *args), true)
    }
}