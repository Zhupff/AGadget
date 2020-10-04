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

        def gadgetSample() {
            apply from: rootProject.file(".script/gadget_sample.gradle")
        }

        def gadgetPlugin() {
            apply from: rootProject.file(".script/gadget_plugin.gradle")
        }

        def gadgetApi() {
            apply from: rootProject.file(".script/gadget_api.gradle")
        }

        def gadgetAnnotation() {
            apply from: rootProject.file(".script/gadget_annotation.gradle")
        }

        def gadgetProcessor() {
            apply from: rootProject.file(".script/gadget_processor.gradle")
        }

        def createDepInfo() {
            apply from: rootProject.file(".script/create_dep_info.gradle")
        }

        def createGadgetInfo() {
            apply from: rootProject.file(".script/create_gadget_info.gradle")
        }

        def jitpackPublish() {
            apply from: rootProject.file(".script/jitpack_publish.gradle")
        }

        def gadgetConvertAnnotation() {
            dependencies {
                implementation project(":${DepInfo.GADGET_CONVERT_ANNOTATION}")
            }
        }
    }
}