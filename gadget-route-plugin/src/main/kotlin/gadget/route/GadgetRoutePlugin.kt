package gadget.route

import gadget.base.plugin.BaseGadgetAppPlugin

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class GadgetRoutePlugin : BaseGadgetAppPlugin() {

    override fun newGadgetTransform() = GadgetRouteTransform()
}