package gadget

import org.gradle.api.Project

class DepBuilder {
    private DepBuilder() {}

    def static build(@DelegatesTo(_DepBuilder.class) Closure closure) {
        closure.delegate = new _DepBuilder(closure.owner)
        closure()
    }


    private static class _DepBuilder {
        @Delegate Project project

        private _DepBuilder(script) {
            project = script.project
        }

        /** Build gradle **/

        def publishGradle(toRemote = false) {
            if (toRemote) {
                apply from: rootProject.file(".script/publish-jitpack.gradle")
            } else {
                apply from: rootProject.file(".script/publish-local.gradle")
            }
        }

        def gadgetBaseGradle() {
            apply from: rootProject.file(".script/gadget-base.gradle")
        }

        def gadgetAndroidLibGradle() {
            apply from: rootProject.file(".script/gadget-android-lib.gradle")
        }

        def gadgetJavaLibGradle() {
            apply from: rootProject.file(".script/gadget-java-lib.gradle")
        }

        def gadgetCompileGradle() {
            apply from: rootProject.file(".script/gadget-compile.gradle")
        }

        def createDepInfo() {
            apply from: rootProject.file(".script/task-create-DepInfo.gradle")
        }

        def createGadgetInfo() {
            apply from: rootProject.file(".script/task-create-GadgetInfo.gradle")
        }

        def createGBaseInfo() {
            apply from: rootProject.file(".script/task-create-GBaseInfo.gradle")
        }

        /** Dependency gradle **/

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

        def gadgetDoRLib() {
            dependencies {
                implementation project(DepInfo.GADGET_DOR_LIB)
            }
        }

        def gadgetDoRCompile() {
            dependencies {
                implementation project(DepInfo.GADGET_DOR_COMPILE)
            }
        }

        def gadgetLogLib() {
            dependencies {
                implementation project(DepInfo.GADGET_LOG_LIB)
            }
        }

        def gadgetLogCompile() {
            dependencies {
                implementation project(DepInfo.GADGET_LOG_COMPILE)
            }
        }
    }
}