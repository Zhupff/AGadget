package gadget.base.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
open class BaseGadgetLibTransform : BaseGadgetTransform() {

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> = TransformManager.PROJECT_ONLY

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