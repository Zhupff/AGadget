package gadget

import org.gradle.api.Project

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description: Gradle dependencies injector.
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

        /**
         * Inject gadget-convert.
         */
        // For java project.
        def gadgetConvert() {
            try {
                dependencies {
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
                dependencies {
                    implementation GadgetInfo.GADGET_CONVERT_ANNOTATION
                    kapt GadgetInfo.GADGET_CONVERT_PROCESSOR
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
    }
}