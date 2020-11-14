package gadget.base.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.QualifiedContent.ContentType
import com.android.build.api.transform.QualifiedContent.Scope
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import gadget.base.plugin.asm.GTransformer
import java.util.ArrayList

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GLibTransform(
    context: GPluginContext,
    transformers: MutableList<GTransformer> = ArrayList())
    : GTransform(context, transformers) {

    override fun getInputTypes(): MutableSet<ContentType> = TransformManager.CONTENT_CLASS

    override fun getScopes(): MutableSet<in Scope> = TransformManager.PROJECT_ONLY

    override fun handleDirInput(dirInput: DirectoryInput, output: TransformOutputProvider) {
        dirInput.file.walk()
            .filter {
                it.isFile && it.name.endsWith(".class")
            }
            .forEach {
                val bytes = handleDirClass(it.name, it.readBytes())
                it.writeBytes(bytes)
            }
        super.handleDirInput(dirInput, output)
    }
}