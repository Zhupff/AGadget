package gadget.api.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import gadget.api.common.Logln
import gadget.api.common.i
import gadget.api.plugin.GadgetBaseTransform.GadgetBaseTransformer
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @Author: Zhupf
 */
class GadgetGradlePlugin : Plugin<Project> {
    companion object {
        /** AGadget 默认流程([GadgetLibTransform]或[GadgetAppTransform])使用的 transformer。 **/
        val transformerMap: HashMap<String, ArrayList<GadgetBaseTransformer>> = HashMap()
        /** 自定义[GadgetBaseTransform]，其所需的 transformer 需自行赋值。 **/
        val transformMap: HashMap<String, ArrayList<GadgetBaseTransform>> = HashMap()
    }

    lateinit var context: GadgetPluginContext

    override fun apply(project: Project) {
        Logln.min("%s apply %s.", i(project.name), i(javaClass.simpleName))

        context = GadgetPluginContext().initWithProject(project)
        val transformers = transformerMap[context.projectName] ?: emptyList<GadgetBaseTransformer>()
        val transforms = transformMap[context.projectName] ?: emptyList<GadgetBaseTransform>()

        if (context.isApplicationProject) {
            project.extensions.findByType(AppExtension::class.java)?.let { ext ->
                if (transformers.isNotEmpty()) {
                    ext.registerTransform(GadgetAppTransform().withContext(context).withTransformers(transformers))
                }
                if (transforms.isNotEmpty()) {
                    transforms.forEach { ext.registerTransform(it.withContext(context)) }
                }
            }
        } else if (context.isAndroidLibraryProject) {
            project.extensions.findByType(LibraryExtension::class.java)?.let { ext ->
                if (transformers.isNotEmpty()) {
                    ext.registerTransform(GadgetLibTransform().withContext(context).withTransformers(transformers))
                }
                if (transforms.isNotEmpty()) {
                    transforms.forEach { ext.registerTransform(it.withContext(context)) }
                }
            }
        }
    }
}