package gadget.base.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GAppPlugin<T : GAppTransform> : GPlugin<T>() {

    override fun apply(project: Project) {
        super.apply(project)
        if (mContext.mIsApplicationProject) {
            project.extensions.findByType(AppExtension::class.java)?.registerTransform(mTransform)
        }
    }
}