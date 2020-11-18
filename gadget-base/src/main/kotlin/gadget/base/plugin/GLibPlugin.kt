package gadget.base.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GLibPlugin<T : GLibTransform> : GPlugin<T>() {

    override fun apply(project: Project) {
        super.apply(project)
        if (this.context.isApplicationProject && this.transform.getTransformerSize() > 0) {
            project.extensions
                .findByType(AppExtension::class.java)
                ?.registerTransform(this.transform)
        } else if (this.context.isAndroidLibraryProject && this.transform.getTransformerSize() > 0) {
            project.extensions
                .findByType(LibraryExtension::class.java)
                ?.registerTransform(this.transform)
        }
    }
}