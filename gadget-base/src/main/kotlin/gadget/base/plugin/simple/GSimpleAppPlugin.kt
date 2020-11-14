package gadget.base.plugin.simple

import gadget.base.plugin.GAppPlugin
import gadget.base.plugin.asm.GTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GSimpleAppPlugin(private vararg val transformers: GTransformer)
    : GAppPlugin<GSimpleAppTransform>() {

    override fun newTransformInstance() = GSimpleAppTransform(mContext, transformers.toMutableList())
}