package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
object G_Router {

    val mRouteTable: HashMap<String, String> = HashMap()

    fun register(vararg table: G_RouteTable) {
        table.forEach { it.register(mRouteTable) }
    }
}