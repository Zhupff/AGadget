package gadget

import gadget.base.plugin.asm.GTransformer
import gadget.base.plugin.simple.GSimpleAppPlugin
import gadget.base.plugin.simple.GSimpleLibPlugin
import gadget.common.GConstants
import gadget.dor.plugin.GDoRTransformer
import gadget.log.plugin.GLogTransformer
import gadget.route.plugin.GRouteTransformer
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
            if (!context.isApplicationProject && !context.isAndroidLibraryProject) {
                throw new IllegalStateException("GadgetBox can only work in Android Application or Library.")
            }
            project.android.defaultConfig.javaCompileOptions.annotationProcessorOptions {
                arguments.put(GConstants.COMPILE_OPTION_PROJECT_NAME, project.name)
            }
        }

        /**
         * Don't forget to close you box.
         * Call this method finally.
         */
        def close() {
            if (!transformers.isEmpty()) {
                if (context.isApplicationProject) {
                    GSimpleAppPlugin.simpleAppTransformers = transformers
                    apply plugin: GSimpleAppPlugin
                } else if (context.isAndroidLibraryProject) {
                    GSimpleLibPlugin.simpleLibTransformersMap[project.name] = transformers
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
            if (!context.isKotlin) {
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

        def gadgetDoRCompile() {
            if (!context.isApplicationProject) {
                throw new IllegalStateException("gadgetDoRCompile() can only work in Android Application.")
            }
            transformers.add(new GDoRTransformer())
        }
        /** Inject gadget-dor. **/


        /** Inject gadget-log **/
        def gadgetLog() {
            dependencies {
                implementation GadgetInfo.GADGET_LOG
            }
        }

        def gadgetLogCompile() {
            dependencies {
                implementation GadgetInfo.GADGET_LOG_LIB
            }
            transformers.add(new GLogTransformer())
        }
        /** Inject gadget-log **/


        /** Inject gadget-route **/
        def gadgetRoute() {
            if (!context.isKotlin) {
                dependencies {
                    implementation GadgetInfo.GADGET_ROUTE_LIB
                    implementation GadgetInfo.GADGET_ROUTE
                    annotationProcessor GadgetInfo.GADGET_ROUTE_COMPILE
                }
            } else {
                dependencies {
                    implementation GadgetInfo.GADGET_ROUTE_LIB
                    implementation GadgetInfo.GADGET_ROUTE
                    kapt GadgetInfo.GADGET_ROUTE_COMPILE
                }
            }
        }

        def gadgetRouteCompile() {
            if (!context.isApplicationProject) {
                throw new IllegalStateException("gadgetRouteCompile() can only work in Android Application.")
            }
            transformers.add(new GRouteTransformer())
        }
        /** Inject gadget-route **/


        /** Inject gadget-scrollview. **/
        def gadgetScrollView() {
            dependencies {
                implementation GadgetInfo.GADGET_SCROLLVIEW
            }
        }
        /** Inject gadget-scrollview. **/


        /** Inject gadget-roundviewview. **/
        def gadgetRoundView() {
            dependencies {
                implementation GadgetInfo.GADGET_ROUNDVIEW
            }
        }
        /** Inject gadget-roundview. **/
    }
}