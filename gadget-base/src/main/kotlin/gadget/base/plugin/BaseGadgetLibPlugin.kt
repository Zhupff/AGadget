package gadget.base.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class BaseGadgetLibPlugin : BaseGadgetPlugin() {

    override fun registerTransform(project: Project) {
        super.registerTransform(project)
        if (isApplicationProject(project)) {
            project.extensions.findByType(AppExtension::class.java)
                ?.registerTransform(newGadgetTransform())
        } else if (isAndroidLibraryProject(project)) {
            project.extensions.findByType(LibraryExtension::class.java)
                ?.registerTransform(newGadgetTransform())
        }
    }

    override fun newGadgetTransform() = BaseGadgetLibTransform()
}