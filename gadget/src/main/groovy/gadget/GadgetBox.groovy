package gadget

import gadget.common.GConstants
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
                    arguments.put(GConstants.COMPILE_OPTION_PROJECT_NAME, mProject.name)
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }


        /** Inject gadget-dor. **/
        def gadgetDoR() {
            try {
                if (!mIsKt) {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_DOR_LIB
                        annotationProcessor GadgetInfo.GADGET_DOR_COMPILE
                    }
                } else {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_DOR_LIB
                        kapt GadgetInfo.GADGET_DOR_COMPILE
                    }
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-dor. **/
    }
}