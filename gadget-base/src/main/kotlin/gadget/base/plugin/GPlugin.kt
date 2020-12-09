package gadget.base.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GPlugin<T : GTransform> : Plugin<Project> {

    protected lateinit var context: GPluginContext
    protected lateinit var transform: T

    override fun apply(project: Project) {
        println("${project.name} apply ${javaClass.simpleName}")
        context = GPluginContext().initWithProject(project)
        transform = newTransformInstance()
    }

    protected abstract fun newTransformInstance(): T
}