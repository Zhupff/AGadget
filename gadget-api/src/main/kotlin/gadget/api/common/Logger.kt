package gadget.api.common

import gadget.api.common.LoggerConfig.LoggerLevel
import groovy.lang.Closure

/**
 * @Author: Zhupf
 */
class LoggerConfig {
    enum class LoggerLevel {
        VERBOSE, DEBUG, INFO, WARN, ERROR
    }

    var minLevel: String? = "v"
    var maxLevel: String? = "e"

    fun getLevel(level: String?): LoggerLevel =
        if ("d".equals(level, true) || "debug".equals(level, true)) {
            LoggerLevel.DEBUG
        } else if ("i".equals(level, true) || "info".equals(level, true)) {
            LoggerLevel.INFO
        } else if ("w".equals(level, true) || "warn".equals(level, true)) {
            LoggerLevel.WARN
        } else if ("e".equals(level, true) || "error".equals(level, true)) {
            LoggerLevel.ERROR
        } else {
            LoggerLevel.VERBOSE
        }
}

object Logger {

    private var minLevel: LoggerLevel = LoggerLevel.VERBOSE
    private var maxLevel: LoggerLevel = LoggerLevel.ERROR

    fun delegate(closure: Closure<Any>) {
        val config = LoggerConfig()
        closure.delegate = config
        closure.call()
        minLevel = config.getLevel(config.minLevel)
        maxLevel = config.getLevel(config.maxLevel)
        log(minLevel, "LoggerConfig: minLevel=${i(minLevel)}, maxLevel=${i(maxLevel)}.", true)
    }

    internal fun v(msg: Any, ln: Boolean) {
        log(LoggerLevel.VERBOSE, msg, ln)
    }

    internal fun d(msg: Any, ln: Boolean) {
        log(LoggerLevel.DEBUG, msg, ln)
    }

    internal fun i(msg: Any, ln: Boolean) {
        log(LoggerLevel.INFO, msg, ln)
    }

    internal fun w(msg: Any, ln: Boolean) {
        log(LoggerLevel.WARN, msg, ln)
    }

    internal fun e(msg: Any, ln: Boolean) {
        log(LoggerLevel.ERROR, msg, ln)
    }

    private fun log(level: LoggerLevel, msg: Any, ln: Boolean) {
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
    fun v(msg: String, vararg args: Any?) {
        Logger.v(String.format(msg, *args), false)
    }

    fun d(msg: String, vararg args: Any?) {
        Logger.d(String.format(msg, *args), false)
    }

    fun i(msg: String, vararg args: Any?) {
        Logger.i(String.format(msg, *args), false)
    }

    fun w(msg: String, vararg args: Any?) {
        Logger.w(String.format(msg, *args), false)
    }

    fun e(msg: String, vararg args: Any?) {
        Logger.e(String.format(msg, *args), false)
    }
}

object Logln {
    fun v(msg: String, vararg args: Any?) {
        Logger.v(String.format(msg, *args), true)
    }

    fun d(msg: String, vararg args: Any?) {
        Logger.d(String.format(msg, *args), true)
    }

    fun i(msg: String, vararg args: Any?) {
        Logger.i(String.format(msg, *args), true)
    }

    fun w(msg: String, vararg args: Any?) {
        Logger.w(String.format(msg, *args), true)
    }

    fun e(msg: String, vararg args: Any?) {
        Logger.e(String.format(msg, *args), true)
    }
}