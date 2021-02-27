package gadget.api.plugin

import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent.Scope
import com.android.build.api.transform.Status
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import gadget.api.common.Logln
import gadget.api.common.i
import java.io.File
import java.io.FileOutputStream
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * @Author: Zhupf
 * Description: 适用于 Application 模块的 transform 处理流程
 */
open class GadgetAppTransform : GadgetLibTransform() {
    override fun getScopes(): MutableSet<in Scope> = TransformManager.SCOPE_FULL_PROJECT

    override fun handleJarInput(input: JarInput, output: TransformOutputProvider) {
        if (incremental) {
            when (input.status) {
                Status.REMOVED -> {
                    Logln.v("%s handleJarInput, input=%s, status=%s.", i(name), i(input.name), i(input.status))
                    input.file.delete()
                    return
                }
                Status.ADDED, Status.CHANGED -> {
                    Logln.d("%s handleJarInput, input=%s, status=%s.", i(name), i(input.name), i(input.status))
                }
                else -> {
                    return
                }
            }
        }
        val tempFile = File("${input.file.parent}${File.separator}temp_classes.jar")
        if (tempFile.exists()) {
            tempFile.delete()
        }
        JarFile(input.file).use { jarFile ->
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
        FileUtils.copyFile(tempFile,
            output.getContentLocation(input.name, input.contentTypes, input.scopes, Format.JAR))
        tempFile.delete()
    }
}