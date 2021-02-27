package gadget.api.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import gadget.api.common.Logln
import gadget.api.common.d
import gadget.api.common.i

/**
 * @Author: Zhupf
 * Description: transform 处理流程基类
 */
abstract class GadgetBaseTransform : Transform() {

    lateinit var context: GadgetPluginContext
        private set
    lateinit var transformers: List<GadgetBaseTransformer>
        private set

    fun withContext(context: GadgetPluginContext) = apply { this.context = context }
    fun withTransformers(transformers: List<GadgetBaseTransformer>) = apply { this.transformers = transformers }
    fun withTransformers(vararg transformers: GadgetBaseTransformer) = apply { this.transformers = transformers.toList() }

    override fun getName(): String = javaClass.simpleName
    override fun isIncremental(): Boolean = true
    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> = TransformManager.CONTENT_CLASS

    protected var incremental: Boolean = false
    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        incremental = transformInvocation?.isIncremental ?: false
        transformInvocation?.outputProvider?.let { outputProvider ->
            context.initWithTransformInvocation(transformInvocation)
            if (!incremental) {
                outputProvider.deleteAll()
            }
            onTransformStart()
            handleTransformInvocation(transformInvocation, outputProvider)
            onTransformStop()
        }
    }

    private var transformStartTime: Long = 0L
    protected open fun onTransformStart() {
        transformStartTime = System.currentTimeMillis()
        Logln.i("%s %s onTransformStart, incremental=%s.", d(transformStartTime), i(name), i(incremental))
        transformers.forEach { it.onTransformStart(context) }
    }

    private var transformStopTime: Long = 0L
    protected open fun onTransformStop() {
        transformers.forEach { it.onTransformStop(context) }
        transformStopTime = System.currentTimeMillis()
        Logln.i("%s %s onTransformStop, duration=%s.", d(transformStopTime), i(name), i(transformStopTime - transformStartTime))
    }

    protected open fun handleTransformInvocation(
        transformInvocation: TransformInvocation, outputProvider: TransformOutputProvider) {
        transformInvocation.inputs.forEach { input ->
            input.directoryInputs.forEach { handleDirInput(it, outputProvider) }
            input.jarInputs.forEach { handleJarInput(it, outputProvider) }
        }
    }

    protected open fun handleDirInput(input: DirectoryInput, output: TransformOutputProvider) {
        FileUtils.copyDirectory(input.file,
            output.getContentLocation(input.name, input.contentTypes, input.scopes, Format.DIRECTORY))
    }

    protected open fun handleJarInput(input: JarInput, output: TransformOutputProvider) {
        FileUtils.copyFile(input.file,
            output.getContentLocation(input.name, input.contentTypes, input.scopes, Format.JAR))
    }

    protected open fun handleDirClass(className: String, classBytes: ByteArray): ByteArray {
        var bytes = classBytes
        transformers.forEach { bytes = it.handleDirClass(className, bytes) }
        return bytes
    }

    protected open fun handleJarClass(className: String, classBytes: ByteArray): ByteArray {
        var bytes = classBytes
        transformers.forEach { bytes = it.handleJarClass(className, bytes) }
        return bytes
    }


    /**
     * @Author: Zhupf
     * Description: .class文件处理器
     */
    interface GadgetBaseTransformer {
        /** 在transform开始时回调 **/
        fun onTransformStart(context: GadgetPluginContext) {
            Logln.d("%s onTransformStart.", i(javaClass.simpleName))
        }

        /** 在transform完成时回调 **/
        fun onTransformStop(context: GadgetPluginContext) {
            Logln.d("%s onTransformStop.", i(javaClass.simpleName))
        }

        /** 根据类名筛选需要处理的.class文件，默认[filterDirClass]和[filterJarClass]都使用此方法 **/
        fun filterClass(className: String): Boolean = false

        /** 根据类名筛选需要过滤的DirectoryInput里的.class文件 **/
        fun filterDirClass(className: String): Boolean = filterClass(className)

        /** 根据类名筛选需要过滤的JarInput里的.class文件 **/
        fun filterJarClass(className: String): Boolean = filterClass(className)

        /** 处理.class文件，默认[transformDirClass]和[transformJarClass]都使用此方法 **/
        fun transformClass(classBytes: ByteArray): ByteArray = classBytes

        /** 处理来自DirectoryInput里的.class文件 **/
        fun transformDirClass(classBytes: ByteArray): ByteArray = transformClass(classBytes)

        /** 处理来自JarInput里的.class文件 **/
        fun transformJarClass(classBytes: ByteArray): ByteArray = transformClass(classBytes)

        /** 管理来自DirectoryInput里的.class文件，满足[filterDirClass]的会进行[transformDirClass] **/
        fun handleDirClass(className: String, classBytes: ByteArray): ByteArray =
            if (filterDirClass(className)) transformDirClass(classBytes) else classBytes

        /** 管理来自DirectoryInput里的.class文件，满足[filterJarClass]的会进行[transformJarClass] **/
        fun handleJarClass(className: String, classBytes: ByteArray): ByteArray =
            if (filterJarClass(className)) transformJarClass(classBytes) else classBytes
    }
}