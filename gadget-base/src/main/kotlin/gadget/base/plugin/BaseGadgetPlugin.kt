package gadget.base.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class BaseGadgetPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("${project.name} apply ${this::class.java.simpleName}")
        registerTransform(project)
    }

    protected open fun registerTransform(project: Project) {}

    abstract fun newGadgetTransform(): BaseGadgetTransform

    protected fun isApplicationProject(project: Project) = project.plugins.hasPlugin("com.android.application")

    protected fun isAndroidLibraryProject(project: Project) = project.plugins.hasPlugin("com.android.library")
}