package gadget.base.processor

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class BaseGadgetProcessor : AbstractProcessor() {

    override fun getSupportedOptions(): Set<String> = emptySet()

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        return typeElementSet != null && roundEnvironment != null
    }
}