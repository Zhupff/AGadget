package gadget.base.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GPlugin<T : GTransform> : Plugin<Project> {

    protected lateinit var mContext: GPluginContext
    protected lateinit var mTransform: T

    override fun apply(project: Project) {
        println("${project.name} apply ${javaClass.simpleName}")
        mContext = GPluginContext(project)
        mTransform = newTransformInstance()
    }

    protected abstract fun newTransformInstance(): T
}