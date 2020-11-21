package gadget.common.view.listener

import android.view.View
import android.view.View.OnClickListener

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */

open class GMultiClickListener(val clickNum: Int = 1, vararg val clickInterval: Long) : OnClickListener {
    companion object {
        const val DEF_INTERVAL: Long = 0L
    }

    private val intervals: LongArray

    init {
        if (clickNum < 1) {
            throw IllegalArgumentException("Click num must >= 1.")
        }
        val lastInterval = if (clickInterval.isNotEmpty()) clickInterval.last() else DEF_INTERVAL
        intervals = LongArray(clickNum - 1) { lastInterval }
        for (i in clickInterval.indices) {
            intervals[i] = clickInterval[i]
        }
    }

    private var count: Int = 0
    private var lastClickTime: Long = 0L
    final override fun onClick(v: View?) {
        val curClickTime = System.currentTimeMillis()
        if (count > 0 && curClickTime <= lastClickTime + intervals[count - 1]) {
            count += 1
        } else {
            count = 1
        }
        onClick(v, count, count == clickNum)
        if (count == clickNum) {
            count = 0
            lastClickTime = 0L
        } else {
            lastClickTime = curClickTime
        }
    }

    open fun onClick(v: View?, count: Int, isLastClick: Boolean) {}
}