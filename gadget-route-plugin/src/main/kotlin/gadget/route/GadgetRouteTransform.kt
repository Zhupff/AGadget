package gadget.route

import com.android.build.api.transform.JarInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import gadget.base.plugin.BaseGadgetAppTransform
import gadget.base.plugin.asm.BaseGadgetTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class GadgetRouteTransform : BaseGadgetAppTransform(), GadgetRouteConstant {

    override fun getTransformers(): List<BaseGadgetTransformer> {
        return listOf(GadgetRouteTransformer())
    }

    override fun handleTransformInvocation(transformInvocation: TransformInvocation, output: TransformOutputProvider) {
        var gadgetRouteJar: JarInput? = null
        transformInvocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { handleDirInput(it, output) }
            transformInput.jarInputs.forEach {
                if (it.name.contains(_GadgetRouteApiJarName)) {
                    gadgetRouteJar = it
                } else {
                    handleJarInput(it, output)
                }
            }
        }
        gadgetRouteJar?.let { handleJarInput(it, output) }
    }
}