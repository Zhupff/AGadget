package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class RouteTable {
    abstract fun register(routeMap: HashMap<String, String>)
}