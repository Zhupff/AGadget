package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
object Router {

    val mRouteTable: HashMap<String, String> = HashMap()

    fun register(vararg table: RouteTable) {
        table.forEach { it.register(mRouteTable) }
    }
}