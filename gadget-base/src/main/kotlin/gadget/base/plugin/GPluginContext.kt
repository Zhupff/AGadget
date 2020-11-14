package gadget.base.plugin

import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class GPluginContext(
    project: Project,
    val mIsApplicationProject: Boolean = project.plugins.hasPlugin("com.android.application"),
    val mIsAndroidLibraryProject: Boolean = project.plugins.hasPlugin("com.android.library"))