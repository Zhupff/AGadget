package gadget.base.plugin.asm

import gadget.base.plugin.GPluginContext

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GTransformer {

    open fun beforeTransform(context: GPluginContext) {}

    open fun afterTransform(context: GPluginContext) {}

    open fun filterClass(className: String): Boolean = true

    open fun filterDirClass(className: String): Boolean = filterClass(className)

    open fun filterJarClass(className: String): Boolean = filterClass(className)

    open fun handleDirClass(className: String, classBytes: ByteArray): ByteArray =
        if (filterDirClass(className)) {
            transformDirClass(classBytes)
        } else classBytes

    open fun handleJarClass(className: String, classBytes: ByteArray): ByteArray =
        if (filterJarClass(className)) {
            transformJarClass(classBytes)
        } else classBytes

    open fun transformClass(classBytes: ByteArray): ByteArray = classBytes

    open fun transformDirClass(classBytes: ByteArray): ByteArray = transformClass(classBytes)

    open fun transformJarClass(classBytes: ByteArray): ByteArray = transformClass(classBytes)
}