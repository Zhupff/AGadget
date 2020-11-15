package gadget.base.plugin.simple

import gadget.base.plugin.GAppPlugin
import gadget.base.plugin.asm.GTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GSimpleAppPlugin : GAppPlugin<GSimpleAppTransform>() {
    companion object {
        var mSimpleAppTransformers: MutableList<GTransformer> = ArrayList()
    }

    override fun newTransformInstance() = GSimpleAppTransform(mContext, mSimpleAppTransformers)
}