package gadget.base.plugin

import com.android.build.api.transform.TransformInvocation
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class GPluginContext {
    private var projectName: String = ""
    private var isApplicationProject: Boolean = false
    private var isAndroidLibraryProject: Boolean = false
    private var debug: Boolean = false

    fun initWithProject(project: Project) = apply {
        this.projectName = project.name
        this.isApplicationProject = project.plugins.hasPlugin("com.android.application")
        this.isAndroidLibraryProject = project.plugins.hasPlugin("com.android.library")
    }

    fun initWithTransformInvocation(transformInvocation: TransformInvocation) = apply {
        this.debug = transformInvocation.context.variantName.equals("debug", true)
    }

    fun projectName(): String = projectName

    fun isApplicationProject(): Boolean = isApplicationProject

    fun isAndroidLibraryProject(): Boolean = isAndroidLibraryProject

    fun debug(): Boolean = debug
}