package gadget.common.view

import android.view.View
import androidx.annotation.MainThread

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
@MainThread
object G_View {

    fun setViewVisibility(view: View?, visible: Int) {
        if (view != null && view.visibility != visible) {
            view.visibility = visible
        }
    }

    fun setViewVisible(view: View?) {
        setViewVisibility(view, View.VISIBLE)
    }

    fun setViewInvisible(view: View?) {
        setViewVisibility(view, View.INVISIBLE)
    }

    fun setViewGone(view: View?) {
        setViewVisibility(view, View.GONE)
    }
}