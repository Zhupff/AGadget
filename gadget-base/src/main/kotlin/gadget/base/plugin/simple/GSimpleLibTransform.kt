package gadget.base.plugin.simple

import gadget.base.plugin.GLibTransform
import gadget.base.plugin.GPluginContext
import gadget.base.plugin.asm.GTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GSimpleLibTransform(
    context: GPluginContext,
    transformers: MutableList<GTransformer> = ArrayList())
    : GLibTransform(context, transformers)