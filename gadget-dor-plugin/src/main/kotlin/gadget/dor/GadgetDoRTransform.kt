package gadget.dor

import com.android.build.api.transform.JarInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import gadget.base.plugin.BaseGadgetAppTransform
import gadget.base.plugin.asm.BaseGadgetTransformer

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
class GadgetDoRTransform : BaseGadgetAppTransform(), GadgetDoRConstant {

    override fun getTransformers(): List<BaseGadgetTransformer> {
        return listOf(GadgetDoRTransformer())
    }

    override fun handleTransformInvocation(transformInvocation: TransformInvocation, output: TransformOutputProvider) {
        var gadgetDoRJar: JarInput? = null
        transformInvocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { handleDirInput(it, output) }
            transformInput.jarInputs.forEach {
                if (it.name.contains(_GadgetDoRAnnotationJarName)) {
                    gadgetDoRJar = it
                } else {
                    handleJarInput(it, output)
                }
            }
        }
        gadgetDoRJar?.let { handleJarInput(it, output) }
    }
}