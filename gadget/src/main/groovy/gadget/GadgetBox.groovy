package gadget

import gadget.dor.GadgetDoRPlugin
import gadget.route.GadgetRoutePlugin
import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description: Gradle dependencies inject helper.
 */
class GadgetBox {
    private GadgetBox() {}

    static void pick(@DelegatesTo(_Gadget.class) Closure closure) {
        closure.delegate = new _Gadget(closure.owner, false)
        closure()
    }

    static void picKt(@DelegatesTo(_Gadget.class) Closure closure) {
        closure.delegate = new _Gadget(closure.owner, true)
        closure()
    }


    private static class _Gadget {
        @Delegate Project mProject
        boolean mIsKt
        boolean mIsApplicationModule
        boolean mIsAndroidLibraryModule

        _Gadget(script, isKt) {
            mProject = script.project
            mIsKt = isKt
            mIsApplicationModule = mProject.plugins.hasPlugin("com.android.application")
            mIsAndroidLibraryModule = mProject.plugins.hasPlugin("com.android.library")
            initAnnotationProcessorOptions()
        }

        private def initAnnotationProcessorOptions() {
            try {
                if (mIsApplicationModule || mIsAndroidLibraryModule) {
                    mProject.android.defaultConfig.javaCompileOptions.annotationProcessorOptions {
                        arguments.put("G_PROJECT_NAME", mProject.name)
                    }
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }


        /** Inject gadget-common. **/
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
        /** Inject gadget-common. **/


        /** Inject gadget-convert. **/
        def gadgetConvert() {
            try {
                if (!mIsKt) {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_CONVERT_ANNOTATION
                        annotationProcessor GadgetInfo.GADGET_CONVERT_PROCESSOR
                    }
                } else {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_CONVERT_ANNOTATION
                        kapt GadgetInfo.GADGET_CONVERT_PROCESSOR
                    }
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-convert. **/


        /** Inject gadget-dor. **/
        def gadgetDoR() {
            try {
                if (!mIsKt) {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_DOR_ANNOTATION
                        annotationProcessor GadgetInfo.GADGET_DOR_PROCESSOR
                    }
                } else {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_DOR_ANNOTATION
                        kapt GadgetInfo.GADGET_DOR_PROCESSOR
                    }
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        def gadgetDoRPlugin() {
            if (!mIsApplicationModule) {
                throw new IllegalStateException(
                    "gadgetDoRPlugin() can only be picked on Application-module.")
            }
            try {
                apply plugin: GadgetDoRPlugin
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-dor. **/


        /** Inject gadget-log. **/
        def gadgetLog() {
            if (!mIsApplicationModule && !mIsAndroidLibraryModule) {
                throw new IllegalStateException(
                    "gadgetLog() can only be picked on Application-module or Android-library-module.")
            }
            try {
                mProject.dependencies {
                    implementation GadgetInfo.GADGET_LOG_API
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-log. **/


        /** Inject gadget-route. **/
        def gadgetRoute() {
            if (!mIsApplicationModule && !mIsAndroidLibraryModule) {
                throw new IllegalStateException(
                    "gadgetRoute() can only be picked on Application-module or Android-library-module.")
            }
            try {
                if (!mIsKt) {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_ROUTE_ANNOTATION
                        implementation GadgetInfo.GADGET_ROUTE_API
                        annotationProcessor GadgetInfo.GADGET_ROUTE_PROCESSOR
                    }
                } else {
                    mProject.dependencies {
                        implementation GadgetInfo.GADGET_ROUTE_ANNOTATION
                        implementation GadgetInfo.GADGET_ROUTE_API
                        kapt GadgetInfo.GADGET_ROUTE_PROCESSOR
                    }
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        def gadgetRoutePlugin() {
            if (!mIsApplicationModule) {
                throw new IllegalStateException(
                    "gadgetRoutePlugin() can only be picked on Application-module.")
            }
            try {
                apply plugin: GadgetRoutePlugin
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        /** Inject gadget-route. **/
    }
}