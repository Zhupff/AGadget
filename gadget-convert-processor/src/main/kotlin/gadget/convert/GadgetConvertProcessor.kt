package gadget.convert

import com.google.auto.service.AutoService
import com.squareup.javapoet.*
import gadget.base.processor.BaseGadgetProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
@AutoService(Processor::class)
class GadgetConvertProcessor : BaseGadgetProcessor() {

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(G_Convert::class.java.canonicalName)

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        if (typeElementSet == null) {
            return false
        }
        roundEnvironment?.getElementsAnnotatedWith(G_Convert::class.java)?.forEach { element ->
            val packageName = processingEnv.elementUtils.getPackageOf(element).qualifiedName.toString()
            val javaFile = JavaFile.builder(packageName, getXxxConvertibleTypeSpec(element)).build()
            try {
                javaFile.writeTo(processingEnv.filer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return true
    }

    private fun getXxxConvertibleTypeSpec(element: Element): TypeSpec {
        val elementName = element.simpleName.toString()
        val interfaceName = "${elementName}Convertible"
        val elementTypeName = ClassName.get(element.asType())
        return TypeSpec.interfaceBuilder(interfaceName)
            .addModifiers(Modifier.PUBLIC)
            .addSuperinterface(G_Convertible::class.java)
            .addMethod(getConvertToMethodSpec(elementName, elementTypeName))
            .addMethod(getConvertFromMethodSpec(elementName, elementTypeName))
            .build()
    }

    private fun getConvertToMethodSpec(elementName: String, elementTypeName: TypeName): MethodSpec {
        return MethodSpec.methodBuilder("convertTo${elementName}")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .returns(elementTypeName)
            .build()
    }

    private fun getConvertFromMethodSpec(elementName: String, elementTypeName: TypeName): MethodSpec {
        return MethodSpec.methodBuilder("convertFrom${elementName}")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .addParameter(elementTypeName, "p0")
            .build()
    }
}