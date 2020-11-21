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

    def static open(@DelegatesTo(_GadgetBox.class) Closure closure) {
        closure.delegate = new _GadgetBox(closure.owner, false)
        closure()
    }

    def static openKt(@DelegatesTo(_GadgetBox.class) Closure closure) {
        closure.delegate = new _GadgetBox(closure.owner, true)
        closure()
    }


    private static class _GadgetBox {
        @Delegate Project project
        GadgetContext context
        ArrayList<GTransformer> transformers = new ArrayList<>()

        _GadgetBox(script, kt = false) {
            this.project = script.project
            this.context = new GadgetContext(this.project, kt)
            _init()
        }

        private def _init() {
            if (!this.context.isApplicationProject && !this.context.isAndroidLibraryProject) {
                throw new IllegalStateException("GadgetBox can only work in Android Application or Library.")
            }
            this.project.android.defaultConfig.javaCompileOptions.annotationProcessorOptions {
                arguments.put(GConstants.COMPILE_OPTION_PROJECT_NAME, this.project.name)
            }
        }

        /**
         * Don't forget to close you box.
         * Call this method finally.
         */
        def close() {
            if (!this.transformers.isEmpty()) {
                if (this.context.isApplicationProject) {
                    GSimpleAppPlugin.simpleAppTransformers = this.transformers
                    apply plugin: GSimpleAppPlugin
                } else if (this.context.isAndroidLibraryProject) {
                    GSimpleLibPlugin.simpleLibTransformersMap[this.project.name] = this.transformers
                    apply plugin: GSimpleLibPlugin
                }
            }
        }


        /** Inject gadget-common. **/
        def gadgetCommonLib() {
            dependencies {
                implementation GadgetInfo.GADGET_COMMON_LIB
            }
        }

        def gadgetCommonModule() {
            dependencies {
                implementation GadgetInfo.GADGET_COMMON_MODULE
            }
        }
        /** Inject gadget-common. **/


        /** Inject gadget-dor. **/
        def gadgetDoR() {
            if (!this.context.isKotlin) {
                dependencies {
                    implementation GadgetInfo.GADGET_DOR_LIB
                    annotationProcessor GadgetInfo.GADGET_DOR_COMPILE
                }
            } else {
                dependencies {
                    implementation GadgetInfo.GADGET_DOR_LIB
                    kapt GadgetInfo.GADGET_DOR_COMPILE
                }
            }
        }

        def gadgetDoRPlugin() {
            if (!this.context.isApplicationProject) {
                throw new IllegalStateException("gadgetDoRPlugin() can only work in Android Application.")
            }
            this.transformers.add(new GDoRTransformer())
        }
        /** Inject gadget-dor. **/
    }
}