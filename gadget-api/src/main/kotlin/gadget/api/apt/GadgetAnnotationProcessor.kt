package gadget.api.apt

import gadget.api.common.Logln
import gadget.api.common.i
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

/**
 * @Author: Zhupf
 * Description:
 */
abstract class GadgetAnnotationProcessor : AbstractProcessor() {

    override fun getSupportedOptions(): MutableSet<String> = mutableSetOf()

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun init(processingEnvironment: ProcessingEnvironment?) {
        super.init(processingEnvironment)
        Logln.i("%s init().", i(javaClass.simpleName))
    }

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        return typeElementSet != null && roundEnvironment != null
    }
}