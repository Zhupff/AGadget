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
        if (context.isApplicationProject && transform.getTransformerSize() > 0) {
            project.extensions
                .findByType(AppExtension::class.java)
                ?.registerTransform(transform)
        } else if (context.isAndroidLibraryProject && transform.getTransformerSize() > 0) {
            project.extensions
                .findByType(LibraryExtension::class.java)
                ?.registerTransform(transform)
        }
    }
}