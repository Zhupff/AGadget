package gadget

import gadget.base.plugin.asm.GTransformer
import gadget.base.plugin.simple.GSimpleAppPlugin
import gadget.base.plugin.simple.GSimpleLibPlugin
import gadget.common.GConstants
import gadget.dor.plugin.GDoRTransformer
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

        ArrayList<GTransformer> mLibTransformers = new ArrayList<>()
        ArrayList<GTransformer> mAppTransformers = new ArrayList<>()

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

        /**
         * Call this method finally.
         */
        def done() {
            try {
                if (!mLibTransformers.isEmpty()) {
                    GSimpleLibPlugin.mSimpleLibTransformersMap[mProject.name] = mLibTransformers
                    apply plugin: GSimpleLibPlugin
                }
                if (!mAppTransformers.isEmpty()) {
                    GSimpleAppPlugin.mSimpleAppTransformers = mAppTransformers
                    apply plugin: GSimpleAppPlugin
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

        def gadgetDoRPlugin() {
            if (!mIsApplicationModule) {
                throw new IllegalStateException("gadgetDoRPlugin() can only work in Android Application.")
            }
            mAppTransformers.add(new GDoRTransformer())
        }
        /** Inject gadget-dor. **/
    }
}