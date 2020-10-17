package gadget

import gadget.dor.GadgetDoRPlugin
import gadget.route.GadgetRoutePlugin
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description: Gradle dependencies inject helper.
 */
class GadgetHelper {
    private GadgetHelper() {}

    static void use(@DelegatesTo(GadgetBuilder.class) Closure closure) {
        closure.delegate = new GadgetBuilder(closure.owner)
        closure()
    }

    private static class GadgetBuilder {
        @Delegate Project mProject

        private GadgetBuilder(script) {
            mProject = script.project
        }

        private def isApplicationModule() {
            return plugins.hasPlugin("com.android.application")
        }

        private def isAndroidLibraryModule() {
            return plugins.hasPlugin("com.android.library")
        }


        /** Inject gadget-common begin. **/
        def gadgetCommonLib() {
            try {
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_COMMON_LIB
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        def gadgetCommonModule() {
            try {
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_COMMON_MODULE
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-common end. **/


        /** Inject gadget-convert begin. **/
        // For java project.
        def gadgetConvert() {
            try {
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_CONVERT_ANNOTATION
                    annotationProcessor GadgetInfo.GADGET_CONVERT_PROCESSOR
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        // For kotlin project.
        def gadgetConvertKt() {
            try {
                if (!plugins.hasPlugin("kotlin-kapt")) {
                    apply plugin: "kotlin-kapt"
                }
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_CONVERT_ANNOTATION
                    kapt GadgetInfo.GADGET_CONVERT_PROCESSOR
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-convert end. **/


        /** Inject gadget-dor begin. **/
        def gadgetDoR() {
            try {
                if (isApplicationModule()) {
                    apply plugin: GadgetDoRPlugin
                }
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_DOR_ANNOTATION
                    annotationProcessor GadgetInfo.GADGET_DOR_PROCESSOR
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        // For kotlin project.
        def gadgetDoRKt() {
            try {
                if (isApplicationModule()) {
                    apply plugin: GadgetDoRPlugin
                }
                if (!plugins.hasPlugin("kotlin-kapt")) {
                    apply plugin: "kotlin-kapt"
                }
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_DOR_ANNOTATION
                    kapt GadgetInfo.GADGET_DOR_PROCESSOR
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-dor end. **/


        /** Inject gadget-route begin. **/
        // For java project.
        def gadgetRoute() {
            if (!isApplicationModule() && !isAndroidLibraryModule()) {
                throw new IllegalStateException(
                    "gadgetRoute() can only use in Application Module or Android Library Module.")
            }
            try {
                mProject.android.defaultConfig.javaCompileOptions.annotationProcessorOptions {
                    arguments.put("GADGET_ROUTE", mProject.name)
                }
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_ROUTE_ANNOTATION
                    implementation GadgetInfo.GADGET_ROUTE_API
                    annotationProcessor GadgetInfo.GADGET_ROUTE_PROCESSOR
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        // For kotlin project.
        def gadgetRouteKt() {
            if (!isApplicationModule() && !isAndroidLibraryModule()) {
                throw new IllegalStateException(
                    "gadgetRouteKt() can only use in Application Module or Android Library Module.")
            }
            try {
                mProject.android.defaultConfig.javaCompileOptions.annotationProcessorOptions {
                    arguments.put("GADGET_ROUTE", mProject.name)
                }
                if (!plugins.hasPlugin("kotlin-kapt")) {
                    apply plugin: "kotlin-kapt"
                }
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_ROUTE_ANNOTATION
                    implementation GadgetInfo.GADGET_ROUTE_API
                    kapt GadgetInfo.GADGET_ROUTE_PROCESSOR
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        def gadgetRoutePlugin() {
            apply plugin: GadgetRoutePlugin
        }
        /** Inject gadget-route end. **/
    }
}