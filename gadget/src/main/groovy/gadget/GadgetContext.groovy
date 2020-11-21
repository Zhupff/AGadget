package gadget

import org.gradle.api.Project

class GadgetContext {

    final boolean isApplicationProject
    final boolean isAndroidLibraryProject
    final boolean isKotlin

    GadgetContext(Project project, boolean kt = false) {
        this.isApplicationProject = project.plugins.hasPlugin("com.android.application")
        this.isAndroidLibraryProject = project.plugins.hasPlugin("com.android.library")
        isKotlin = kt
    }
}