package gadget.base.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileOutputStream
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
open class BaseGadgetAppTransform : BaseGadgetTransform() {

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> = TransformManager.SCOPE_FULL_PROJECT

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