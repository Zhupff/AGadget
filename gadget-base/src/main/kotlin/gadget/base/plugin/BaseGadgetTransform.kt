package gadget.base.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
abstract class BaseGadgetTransform : Transform() {

    protected val mTransformers: ArrayList<BaseGadgetTransformer> = ArrayList()

    override fun getName(): String = this::class.java.simpleName

    override fun isIncremental(): Boolean = true

    override fun getInputTypes(): Set<QualifiedContent.ContentType> = TransformManager.CONTENT_CLASS

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        transformInvocation?.outputProvider?.let { output ->
            output.deleteAll()
            setTransformers(getTransformers())
            handleTransformInvocation(transformInvocation, output)
        }
    }

    protected open fun setTransformers(transformers: List<BaseGadgetTransformer>) {
        mTransformers.clear()
        mTransformers.addAll(transformers)
    }

    protected open fun getTransformers(): List<BaseGadgetTransformer> = emptyList()

    protected open fun handleTransformInvocation(transformInvocation: TransformInvocation, output: TransformOutputProvider) {
        transformInvocation.inputs.parallelStream().forEach { transformInput ->
            transformInput.directoryInputs.parallelStream().forEach { handleDirInput(it, output) }
            transformInput.jarInputs.parallelStream().forEach { handleJarInput(it, output) }
        }
    }

    protected open fun handleDirInput(dirInput: DirectoryInput, output: TransformOutputProvider) {
        FileUtils.copyDirectory(
            dirInput.file,
            output.getContentLocation(
                dirInput.name,
                dirInput.contentTypes,
                dirInput.scopes,
                Format.DIRECTORY))
    }

    protected open fun handleJarInput(jarInput: JarInput, output: TransformOutputProvider) {
        FileUtils.copyFile(
            jarInput.file,
            output.getContentLocation(
                jarInput.name,
                jarInput.contentTypes,
                jarInput.scopes,
                Format.JAR))
    }

    protected open fun handleDirClass(className: String, classBytes: ByteArray): ByteArray {
        var classBytes = classBytes
        mTransformers.forEach { transformer ->
            classBytes = transformer.handleDirClass(className, classBytes)
        }
        return classBytes
    }

    protected open fun handleJarClass(className: String, classBytes: ByteArray): ByteArray {
        var classBytes = classBytes
        mTransformers.forEach { transformer ->
            classBytes = transformer.handleJarClass(className, classBytes)
        }
        return classBytes
    }
}