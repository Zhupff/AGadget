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
        @Delegate Project project
        boolean isKt
        boolean isApplicationModule
        boolean isAndroidLibraryModule

        ArrayList<GTransformer> libTransformers = new ArrayList<>()
        ArrayList<GTransformer> appTransformers = new ArrayList<>()

        _GadgetBox(script, isKt) {
            this.project = script.project
            this.isKt = isKt
            this.isApplicationModule = this.project.plugins.hasPlugin("com.android.application")
            this.isAndroidLibraryModule = this.project.plugins.hasPlugin("com.android.library")
            openGadgetBox()
        }

        private def openGadgetBox() {
            if (!this.isApplicationModule && !this.isAndroidLibraryModule) {
                throw new IllegalStateException("GadgetBox can only work in Android Application or Library.")
            }
            try {
                this.project.android.defaultConfig.javaCompileOptions.annotationProcessorOptions {
                    arguments.put(GConstants.COMPILE_OPTION_PROJECT_NAME, this.project.name)
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
                if (!this.libTransformers.isEmpty()) {
                    GSimpleLibPlugin.simpleLibTransformersMap[this.project.name] = this.libTransformers
                    apply plugin: GSimpleLibPlugin
                }
                if (!this.mAppTransformers.isEmpty()) {
                    GSimpleAppPlugin.simpleAppTransformers = this.appTransformers
                    apply plugin: GSimpleAppPlugin
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }


        /** Inject gadget-dor. **/
        def gadgetDoR() {
            try {
                if (!this.isKt) {
                    this.project.dependencies {
                        implementation GadgetInfo.GADGET_DOR_LIB
                        annotationProcessor GadgetInfo.GADGET_DOR_COMPILE
                    }
                } else {
                    this.project.dependencies {
                        implementation GadgetInfo.GADGET_DOR_LIB
                        kapt GadgetInfo.GADGET_DOR_COMPILE
                    }
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        def gadgetDoRPlugin() {
            if (!this.isApplicationModule) {
                throw new IllegalStateException("gadgetDoRPlugin() can only work in Android Application.")
            }
            this.appTransformers.add(new GDoRTransformer())
        }
        /** Inject gadget-dor. **/
    }
}