package gadget

import org.gradle.api.Project

class DepHelper {
    private DepHelper() {}

    def static build(@DelegatesTo(DepBuilder.class) Closure closure) {
        closure.delegate = new DepBuilder(closure.owner)
        closure()
    }


    private static class DepBuilder {
        @Delegate Project mProject

        private DepBuilder(script) {
            mProject = script.project
        }

        /* Build.gradle */

        def gadgetSampleGradle() {
            apply from: rootProject.file(".script/gadget_sample.gradle")
        }

        def gadgetBaseGradle() {
            apply from: rootProject.file(".script/gadget_base.gradle")
        }

        def gadgetPluginGradle() {
            apply from: rootProject.file(".script/gadget_plugin.gradle")
        }

        def gadgetApiGradle() {
            apply from: rootProject.file(".script/gadget_api.gradle")
        }

        def gadgetAnnotationGradle() {
            apply from: rootProject.file(".script/gadget_annotation.gradle")
        }

        def gadgetProcessorGradle() {
            apply from: rootProject.file(".script/gadget_processor.gradle")
        }

        def createDepInfoGradle() {
            apply from: rootProject.file(".script/create_dep_info.gradle")
        }

        def createGadgetInfoGradle() {
            apply from: rootProject.file(".script/create_gadget_info.gradle")
        }

        def jitpackPublishGradle() {
            apply from: rootProject.file(".script/jitpack_publish.gradle")
        }

        /* Module dependency */

        def gadgetBase() {
            dependencies {
                implementation project(DepInfo.GADGET_BASE)
            }
        }

        def gadgetCommonLib() {
            dependencies {
                implementation project(DepInfo.GADGET_COMMON_LIB)
            }
        }

        def gadgetCommonModule() {
            dependencies {
                implementation project(DepInfo.GADGET_COMMON_MODULE)
            }
        }

        def gadgetConvertAnnotation() {
            dependencies {
                implementation project(DepInfo.GADGET_CONVERT_ANNOTATION)
            }
        }

        def gadgetDoRAnnotation() {
            dependencies {
                implementation project(DepInfo.GADGET_DOR_ANNOTATION)
            }
        }

        def gadgetDoRPlugin() {
            dependencies {
                implementation project(DepInfo.GADGET_DOR_PLUGIN)
            }
        }

        def gadgetRouteAnnotation() {
            dependencies {
                implementation project(DepInfo.GADGET_ROUTE_ANNOTATION)
            }
        }

        def gadgetRoutePlugin() {
            dependencies {
                implementation project(DepInfo.GADGET_ROUTE_PLUGIN)
            }
        }
    }
}