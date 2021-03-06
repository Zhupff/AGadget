package gadget.api.plugin

import com.android.build.api.transform.TransformInvocation
import org.gradle.api.Project

/**
 * @Author: Zhupf
 */
class GadgetPluginContext {

    // <editor-fold desc="需要用Project初始化的信息">
    /** 目标模块名称 **/
    lateinit var projectName: String
    /** 是否是 Application模块 **/
    var isApplicationProject: Boolean = false
    /** 是否是 Android-Library模块 **/
    var isAndroidLibraryProject: Boolean = false

    /** 使用[project]初始化必要信息 **/
    fun initWithProject(project: Project) = apply {
        this.projectName = project.name
        this.isApplicationProject = project.plugins.hasPlugin("com.android.application")
        this.isAndroidLibraryProject = project.plugins.hasPlugin("com.android.library")
    }
    // </editor-fold>

    // <editor-fold desc="需要用TransformInvocation初始化的信息">
    /** 目标模块是否是处于Debug模式 **/
    var debug: Boolean? = null
        private set

    /** 使用[TransformInvocation]初始化必要信息 **/
    fun initWithTransformInvocation(transformInvocation: TransformInvocation) = apply {
        this.debug = transformInvocation.context.variantName.equals("debug", true)
    }
    // </editor-fold>
}