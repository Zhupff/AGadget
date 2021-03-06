package gadget

import gadget.api.closure.GadgetLoggerClosure
import gadget.api.closure.GadgetTransformClosure
import gadget.api.common.Logger
import gadget.api.plugin.GadgetBaseTransform
import gadget.api.plugin.GadgetBaseTransform.GadgetBaseTransformer
import gadget.api.plugin.GadgetGradlePlugin
import org.gradle.api.Project

final class Gadget {
    private Gadget() {}

    def static compose(@DelegatesTo(GadgetComposer.class) Closure closure) {
        GadgetComposer composer = new GadgetComposer(closure.owner)
        closure.delegate = composer
        closure.call()
        composer.compose()
    }

    private static class GadgetComposer {
        @Delegate Project project
        private boolean isApplicationProject
        private boolean isAndroidLibraryProject
        private ArrayList<GadgetBaseTransformer> transformers = new ArrayList<>()
        private ArrayList<GadgetBaseTransform> transforms = new ArrayList<>()

        GadgetComposer(script) {
            assert script.project instanceof Project
            this.project = script.project
            isApplicationProject = project.plugins.hasPlugin("com.android.application")
            isAndroidLibraryProject = project.plugins.hasPlugin("com.android.library")
            if (!isApplicationProject && !isAndroidLibraryProject) {
                throw new IllegalStateException("AGadget can only run on Application project or Android-Library project!")
            }
        }

        private def compose() {
            if (isApplicationProject || isAndroidLibraryProject) {
                GadgetGradlePlugin.transformerMap[project.name] = transformers
                GadgetGradlePlugin.transformMap[project.name] = transforms
                apply plugin: GadgetGradlePlugin
            }
        }

        def logger(Closure closure) {
            GadgetLoggerClosure c = new GadgetLoggerClosure()
            c.delegate(closure)
            Logger.INSTANCE.init(c)
        }

        def transform(Closure closure) {
            GadgetTransformClosure c = new GadgetTransformClosure()
            c.delegate(closure)
            transformers.addAll(c.transformers)
            transforms.addAll(c.transforms)
        }
    }
}