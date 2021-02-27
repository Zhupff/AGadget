package gadget.api.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent.Scope
import com.android.build.api.transform.Status
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import gadget.api.common.Logln
import gadget.api.common.i
import java.io.File
import java.io.IOException

/**
 * @Author: Zhupf
 * Description: 适用于 Android-Library 模块的 transform 处理流程
 */
open class GadgetLibTransform : GadgetBaseTransform() {
    override fun getScopes(): MutableSet<in Scope> = TransformManager.PROJECT_ONLY

    override fun handleDirInput(input: DirectoryInput, output: TransformOutputProvider) {
        Logln.v("%s handleDirInput, input=%s.", i(name), i(input.name))
        val desDir = output.getContentLocation(input.name, input.contentTypes, input.scopes, Format.DIRECTORY)
        if (incremental) {
            val srcDirPath = input.file.absolutePath
            val desDirPath = desDir.absolutePath
            input.changedFiles.forEach { (file, status) ->
                when (status) {
                    Status.ADDED, Status.CHANGED -> {
                        Logln.d("%s's status's %s.", i(file.name), i(status))
                        val desFile = File(file.absolutePath.replace(srcDirPath, desDirPath))
                        if (file.isFile) {
                            val desFileParent = file.parentFile
                            if (desFileParent != null && !desFileParent.mkdirs() && !desFileParent.isDirectory) {
                                throw IOException("Can't create directory(${desFileParent.absolutePath}).")
                            }
                            if (!file.exists()) {
                                file.createNewFile()
                            }
                        }
                        if (file.isFile) {
                            if (file.name.endsWith(".class")) {
                                val bytes = handleDirClass(file.name, file.readBytes())
                                file.writeBytes(bytes)
                            }
                            FileUtils.copyFile(file, desFile)
                        }
                    }
                    Status.REMOVED -> {
                        Logln.v("%s's status's %s.", i(file.name), i(status))
                        FileUtils.deleteIfExists(file)
                    }
                    else -> { // Status.UNCHANGED or null
                        // do nothing.
                    }
                }
            }
        } else {
            input.file.walk()
                .filter {
                    it.isFile && it.name.endsWith(".class")
                }
                .forEach {
                    val bytes = handleDirClass(it.name, it.readBytes())
                    it.writeBytes(bytes)
                }
            FileUtils.copyDirectory(input.file, desDir)
        }
    }
}