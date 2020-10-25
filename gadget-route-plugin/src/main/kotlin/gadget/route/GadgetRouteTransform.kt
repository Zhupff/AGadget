package gadget.route

import com.android.build.api.transform.JarInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import gadget.base.plugin.BaseGadgetAppTransform
import gadget.base.plugin.asm.BaseGadgetTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
class GadgetRouteTransform : BaseGadgetAppTransform(), GadgetRouteConstant {

    override fun getTransformers(): List<BaseGadgetTransformer> {
        return listOf(GadgetRouteTransformer())
    }

    override fun handleTransformInvocation(transformInvocation: TransformInvocation, output: TransformOutputProvider) {
        var gadgetRouteJar: JarInput? = null
        transformInvocation.inputs.parallelStream().forEach { transformInput ->
            transformInput.directoryInputs.parallelStream().forEach { handleDirInput(it, output) }
            transformInput.jarInputs.parallelStream().forEach {
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