package gadget.base.processor

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
abstract class GAnnotationProcessor : AbstractProcessor() {

    override fun getSupportedOptions(): MutableSet<String> = mutableSetOf()

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun init(processingEnvironment: ProcessingEnvironment?) {
        super.init(processingEnvironment)
    }

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        return typeElementSet != null && roundEnvironment != null
    }
}