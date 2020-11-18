package gadget.base.plugin

import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class GPluginContext(
    project: Project,
    val projectName: String = project.name,
    val isApplicationProject: Boolean = project.plugins.hasPlugin("com.android.application"),
    val isAndroidLibraryProject: Boolean = project.plugins.hasPlugin("com.android.library"))