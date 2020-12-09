package gadget.route

import android.content.Context
import android.content.Intent
import gadget.common.GConstants
import gadget.common.model.GMut2Tuple
import gadget.common.g.GTask

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class GRouteTask(
    val context: Context,
    var intent: Intent = Intent()
) : GTask {

    private val request: GMut2Tuple<Boolean, Int> = GMut2Tuple(false, GConstants.DEF_INT)
    private var needFinish: Boolean = false

    fun to(route: String) = apply { }

    fun withRequest(code: Int) = apply {
        this.request.first = true
        this.request.second = code
    }

    fun withoutRequest() = apply { this.request.first = false }

    fun withFinish() = apply { this.needFinish = true }

    fun withoutFinish() = apply { this.needFinish = false }

    override fun ready() {
    }

    override fun run() {
    }
}