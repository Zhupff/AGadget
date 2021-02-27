package gadget

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
        GadgetContext context

        GadgetComposer(script) {
            assert script.project instanceof Project
            this.project = script.project
            this.context = new GadgetContext(this.project)
            assert context.isApplicationProject || context.isAndroidLibraryProject
        }

        private def compose() {
            if (context.isApplicationProject || context.isAndroidLibraryProject) {
                GadgetGradlePlugin.transformerMap.put(project.name, context.getTransformers())
                GadgetGradlePlugin.transformMap.put(project.name, context.getTransforms())
                apply plugin: GadgetGradlePlugin
            }
        }

        def logger(Closure closure) {
            Logger.INSTANCE.delegate(closure)
        }

        def transform(Closure closure) {
            this.context.delegate(closure)
        }
    }

    private static class GadgetContext {

        boolean isApplicationProject = false
        boolean isAndroidLibraryProject = false
        ArrayList<GadgetBaseTransformer> transformers = null
        ArrayList<GadgetBaseTransform> transforms = null

        GadgetContext(Project project) {
            isApplicationProject = project.plugins.hasPlugin("com.android.application")
            isAndroidLibraryProject = project.plugins.hasPlugin("com.android.library")
        }

        def delegate(Closure closure) {
            closure.delegate = this
            closure.call()
        }

        List<GadgetBaseTransformer> getTransformers() {
            if (transformers != null) {
                return transformers
            }
            return new ArrayList<GadgetBaseTransformer>(0)
        }

        List<GadgetBaseTransform> getTransforms() {
            if (transforms != null) {
                return transforms
            }
            return new ArrayList<GadgetBaseTransform>(0)
        }
    }
}