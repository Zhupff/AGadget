package gadget.route

import gadget.base.plugin.BaseGadgetAppPlugin

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
class GadgetRoutePlugin : BaseGadgetAppPlugin() {

    override fun newGadgetTransform() = GadgetRouteTransform()
}