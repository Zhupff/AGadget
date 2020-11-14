package gadget.base.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent.ContentType
import com.android.build.api.transform.QualifiedContent.Scope
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import gadget.base.plugin.asm.GTransformer
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileOutputStream
import java.util.ArrayList
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class GAppTransform(
    context: GPluginContext,
    transformers: MutableList<GTransformer> = ArrayList())
    : GTransform(context, transformers) {

    override fun getInputTypes(): MutableSet<ContentType> = TransformManager.CONTENT_CLASS

    override fun getScopes(): MutableSet<in Scope> = TransformManager.SCOPE_FULL_PROJECT

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

    override fun handleJarInput(jarInput: JarInput, output: TransformOutputProvider) {
        val tempFile = File("${jarInput.file.parent}${File.separator}temp_classes.jar")
        if (tempFile.exists()) {
            tempFile.delete()
        }
        JarFile(jarInput.file).use { jarFile ->
            FileOutputStream(tempFile).use { fos ->
                JarOutputStream(fos).use { jos ->
                    jarFile.entries().toList().forEach { jarEntry ->
                        jarFile.getInputStream(jarEntry).use { ins ->
                            var bytes = ins.readBytes()
                            if (jarEntry.name.endsWith(".class")) {
                                bytes = handleJarClass(jarEntry.name, bytes)
                            }
                            jos.putNextEntry(ZipEntry(jarEntry.name))
                            jos.write(bytes)
                            jos.closeEntry()
                        }
                    }
                }
            }
        }
        FileUtils.copyFile(
            tempFile,
            output.getContentLocation(
                jarInput.name,
                jarInput.contentTypes,
                jarInput.scopes,
                Format.JAR
            )
        )
        tempFile.delete()
    }
}