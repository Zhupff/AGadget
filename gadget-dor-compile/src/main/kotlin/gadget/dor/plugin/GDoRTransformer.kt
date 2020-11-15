package gadget.dor.plugin

import gadget.base.plugin.GPluginContext
import gadget.base.plugin.asm.GTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class GDoRTransformer : GTransformer() {

    override fun beforeTransform(context: GPluginContext) {
        super.beforeTransform(context)
        println("GDoRTransformer beforeTransform")
    }
}