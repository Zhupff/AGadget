package gadget.base.plugin.simple

import com.android.build.api.transform.JarInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import gadget.base.GadgetInfo
import gadget.base.plugin.GAppTransform
import gadget.base.plugin.GPluginContext
import gadget.base.plugin.asm.GTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GSimpleAppTransform(
    context: GPluginContext,
    transformers: MutableList<GTransformer> = ArrayList())
    : GAppTransform(context, transformers) {

    override fun handleTransformInvocation(invocation: TransformInvocation, output: TransformOutputProvider) {
        val gadgetJars: ArrayList<JarInput> = ArrayList()
        invocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { handleDirInput(it, output) }
            transformInput.jarInputs.forEach {
                if (it.name.startsWith(GadgetInfo.GADGET_GROUP)) {
                    gadgetJars.add(it)
                } else {
                    handleJarInput(it, output)
                }
            }
        }
        gadgetJars.forEach { handleJarInput(it, output) }
    }
}