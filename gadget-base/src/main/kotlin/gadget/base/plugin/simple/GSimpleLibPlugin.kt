package gadget.base.plugin.simple

import gadget.base.plugin.GLibPlugin
import gadget.base.plugin.asm.GTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GSimpleLibPlugin : GLibPlugin<GSimpleLibTransform>() {
    companion object {
        val mSimpleLibTransformersMap: MutableMap<String, MutableList<GTransformer>> = HashMap()
    }

    override fun newTransformInstance() = GSimpleLibTransform(
        mContext, mSimpleLibTransformersMap[mContext.projectName] ?: mutableListOf())
}