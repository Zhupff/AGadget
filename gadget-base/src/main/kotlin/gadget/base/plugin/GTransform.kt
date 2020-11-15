package gadget.base.plugin

import com.android.build.api.transform.*
import gadget.base.plugin.asm.GTransformer
import org.apache.commons.io.FileUtils

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GTransform(
    protected open val mContext: GPluginContext,
    protected open val mTransformers: MutableList<GTransformer> = ArrayList())
    : Transform() {

    override fun getName(): String = javaClass.simpleName

    override fun isIncremental(): Boolean = true

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        transformInvocation?.outputProvider?.let { output ->
            output.deleteAll()
            beforeTransform()
            handleTransformInvocation(transformInvocation, output)
            afterTransform()
        }
    }

    protected open fun beforeTransform() {
        mTransformers.forEach { it.beforeTransform(mContext) }
    }

    protected open fun afterTransform() {
        mTransformers.forEach { it.afterTransform(mContext) }
    }

    protected open fun handleTransformInvocation(
        invocation: TransformInvocation, output: TransformOutputProvider) {
        invocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { handleDirInput(it, output) }
            transformInput.jarInputs.forEach { handleJarInput(it, output) }
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
        var bytes = classBytes
        mTransformers.forEach { bytes = it.handleDirClass(className, bytes) }
        return bytes
    }

    protected open fun handleJarClass(className: String, classBytes: ByteArray): ByteArray {
        var bytes = classBytes
        mTransformers.forEach { bytes = it.handleJarClass(className, bytes) }
        return bytes
    }

    fun getTransformerSize(): Int = mTransformers.size
}