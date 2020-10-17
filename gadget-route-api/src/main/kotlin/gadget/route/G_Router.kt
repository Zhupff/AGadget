package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
object G_Router {

    private val mRouteTable: HashMap<String, String> = HashMap()

    init {
        initRouteTable().forEach { it.register(mRouteTable)}
    }

    private fun initRouteTable(): List<G_RouteTable> = emptyList()

    fun registerRouteTable(vararg table: G_RouteTable) {
        table.forEach { it.register(mRouteTable) }
    }
}