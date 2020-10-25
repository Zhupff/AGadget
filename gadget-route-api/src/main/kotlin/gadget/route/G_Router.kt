package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
object G_Router {

    private val mRouteTable: HashMap<String, String> = HashMap()

    init {
        _getTaG_Route().forEach { it.register(mRouteTable)}
    }

    /**
     * If you pick gadgetRoutePlugin(), This method will be rewritten During Compilation.
     */
    private fun _getTaG_Route(): List<TaG_Route> = emptyList()

    fun register(vararg route: TaG_Route) {
        route.forEach { it.register(mRouteTable) }
    }
}