package gadget.route

import android.content.Context
import android.content.Intent
import gadget.common.model.G_MutDouble

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class G_RouteTask(
    protected val mContext: Context,
    protected val mIntent: Intent) {

    protected val mRequestCode: G_MutDouble<Boolean, Int> = G_MutDouble(false, 0)
    protected var mIsFinish: Boolean = false
//    protected var mRouteParser: G_RouteParser = G_BaseRouteParser()
//    protected var mInterceptor: G_RouteInterceptor = G_DefRouteInterceptor()

    fun to(path: String) = apply {
    }
}