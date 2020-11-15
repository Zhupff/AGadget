package gadget

import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
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
            openGadgetBox()
        }

        private def openGadgetBox() {
            if (!mIsApplicationModule && !mIsAndroidLibraryModule) {
                throw new IllegalStateException("GadgetBox can only work in Android Application or Library.")
            }
            try {
                mProject.android.defaultConfig.javaCompileOptions.annotationProcessorOptions {
                    arguments.put(GadgetConstant.G_PROJECT_NAME, mProject.name)
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
    }
}