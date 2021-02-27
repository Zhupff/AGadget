package gadget.api.plugin

import com.android.build.api.transform.TransformInvocation
import org.gradle.api.Project

/**
 * @Author: Zhupf
 */
class GadgetPluginContext(project: Project) {

    /** 目标模块名称 **/
    val projectName: String = project.name

    /** 是否是 Application模块 **/
    val isApplicationProject: Boolean = project.plugins.hasPlugin("com.android.application")

    /** 是否是 Android-Library模块 **/
    val isAndroidLibraryProject: Boolean = project.plugins.hasPlugin("com.android.library")

    /** 目标模块是否是处于Debug模式 **/
    var debug: Boolean? = null
        private set

    /** 使用 [TransformInvocation] 初始化必要信息 **/
    fun initWithTransformInvocation(transformInvocation: TransformInvocation) = apply {
        this.debug = transformInvocation.context.variantName.equals("debug", true)
    }
}