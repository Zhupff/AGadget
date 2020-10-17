package gadget.route

import gadget.base.plugin.BaseGadgetAppTransform
import gadget.base.plugin.BaseGadgetTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
class GadgetRouteTransform : BaseGadgetAppTransform() {

    override fun getTransformers(): List<BaseGadgetTransformer> {
        return listOf(GadgetRouteTransformer())
    }
}