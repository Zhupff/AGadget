package gadget.base.plugin.simple

import gadget.base.plugin.GLibPlugin
import gadget.base.plugin.asm.GTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GSimpleLibPlugin(private vararg val transformers: GTransformer)
    : GLibPlugin<GSimpleLibTransform>() {

    override fun newTransformInstance() = GSimpleLibTransform(mContext, transformers.toMutableList())
}