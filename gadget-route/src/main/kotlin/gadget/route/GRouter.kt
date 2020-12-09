package gadget.route

import android.content.Context
import android.content.Intent

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
object GRouter {

    private val routeMap: MutableMap<String, String> = HashMap()

    fun init(vararg args: TaGRouteUrl) {
        getTaGRouteUrlList().forEach { it.init(routeMap) }
        args.forEach { it.init(routeMap) }
    }

    /**
     * If you pick gadgetRouteCompile(), This method will be rewritten During Compilation.
     */
    private fun getTaGRouteUrlList(): List<TaGRouteUrl> = emptyList()

    fun from(context: Context): GRouteTask = GRouteTask(context)

    fun from(context: Context, intent: Intent): GRouteTask = GRouteTask(context, intent)
}