package gadget.base.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class BaseGadgetAppPlugin : BaseGadgetPlugin() {

    override fun registerTransform(project: Project) {
        super.registerTransform(project)
        if (isApplicationProject(project)) {
            project.extensions.findByType(AppExtension::class.java)
                ?.registerTransform(newGadgetTransform())
        }
    }

    override fun newGadgetTransform() = BaseGadgetAppTransform()
}