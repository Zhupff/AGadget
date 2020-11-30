package gadget.log

import android.util.Log

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
internal enum class GLogLevel(val level: Int) {
    VERBOSE(Log.VERBOSE),
    DEBUG(Log.DEBUG),
    INFO(Log.INFO),
    WARN(Log.WARN),
    ERROR(Log.ERROR);
}