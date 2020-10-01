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

        def gadgetApi() {
            apply from: rootProject.file(".script/gadget_api.gradle")
        }

        def gadgetAnnotation() {
            apply from: rootProject.file(".script/gadget_annotation.gradle")
        }

        def gadgetProcessor() {
            apply from: rootProject.file(".script/gadget_processor.gradle")
        }

        def gadgetConvertAnnotation() {
            dependencies {
                implementation project(":${DepInfo.GADGET_CONVERT_ANNOTATION}")
            }
        }
    }
}