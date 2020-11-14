package gadget

import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description: Gradle dependencies inject helper.
 */
class GadgetBox {
    private GadgetBox() {}

    static void pick(@DelegatesTo(_GadgetBox.class) Closure closure) {
        closure.delegate = new _GadgetBox(closure.owner, false)
        closure()
    }

    static void picKt(@DelegatesTo(_GadgetBox.class) Closure closure) {
        closure.delegate = new _GadgetBox(closure.owner, true)
        closure()
    }


    private static class _GadgetBox {
        @Delegate Project mProject
        boolean mIsKt
        boolean mIsApplicationModule
        boolean mIsAndroidLibraryModule

        _GadgetBox(script, isKt) {
            mProject = script.project
            mIsKt = isKt
            mIsApplicationModule = mProject.plugins.hasPlugin("com.android.application")
            mIsAndroidLibraryModule = mProject.plugins.hasPlugin("com.android.library")
        }
    }
}