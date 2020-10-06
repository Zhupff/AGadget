package gadget.base

import javax.annotation.processing.AbstractProcessor
import javax.lang.model.SourceVersion

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class BaseGadgetProcessor : AbstractProcessor() {

    override fun getSupportedOptions(): Set<String> = emptySet()

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()
}