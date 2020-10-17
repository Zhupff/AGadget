package gadget.route

import gadget.base.plugin.BaseGadgetTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
class GadgetRouteTransformer : BaseGadgetTransformer() {
    override fun filterClass(className: String): Boolean {
        if (className.contains("_RouteTable.class")) {
            println("Find $className")
        }
        return super.filterClass(className)
    }
}