package gadget.route

import gadget.common.g.TaG

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
interface TaGRouteUrl : TaG {

    override fun tag() = "TaGRouteUrl"

    fun init(routeMap: MutableMap<String, String>)
}


interface TaGRouteParser : TaG {

    override fun tag() = "TaGRouteParser"
}