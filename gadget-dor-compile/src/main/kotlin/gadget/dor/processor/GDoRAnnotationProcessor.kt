package gadget.dor.processor

import com.google.auto.common.SuperficialValidation
import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import gadget.base.processor.GAnnotationProcessor
import gadget.common.GConstants
import gadget.common.util.GString
import gadget.dor.*
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
class GDoRAnnotationProcessor : GAnnotationProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> = mutableSetOf(
        GDoRByte::class.java.canonicalName,
        GDoRShort::class.java.canonicalName,
        GDoRInt::class.java.canonicalName,
        GDoRLong::class.java.canonicalName,
        GDoRFloat::class.java.canonicalName,
        GDoRDouble::class.java.canonicalName,
        GDoRBoolean::class.java.canonicalName,
        GDoRChar::class.java.canonicalName,
        GDoRShort::class.java.canonicalName
    )

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        if (typeElementSet == null || roundEnvironment == null) {
            return false
        }
        val projectName = processingEnv.options[GConstants.COMPILE_OPTION_PROJECT_NAME]
        if (GString.isNullOrEmpty(projectName)) {
            return false
        }

        val fields = HashMap<String, ArrayList<GDoRField<*>>>()
        processGDoRByteField(roundEnvironment, fields)
        processGDoRShortField(roundEnvironment, fields)
        processGDoRIntField(roundEnvironment, fields)
        processGDoRLongField(roundEnvironment, fields)
        processGDoRFloatField(roundEnvironment, fields)
        processGDoRDoubleField(roundEnvironment, fields)
        processGDoRBooleanField(roundEnvironment, fields)
        processGDoRCharField(roundEnvironment, fields)
        processGDoRStringField(roundEnvironment, fields)

        generatePROJECT_GDoRClass(projectName!!, fields)
        return super.process(typeElementSet, roundEnvironment)
    }

    private fun processGDoRByteField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRByte::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRByte::class.java)
                val field = GDoRByteField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRShortField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRShort::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRShort::class.java)
                val field = GDoRShortField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRIntField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRInt::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRInt::class.java)
                val field = GDoRIntField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRLongField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRLong::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRLong::class.java)
                val field = GDoRLongField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRFloatField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRFloat::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRFloat::class.java)
                val field = GDoRFloatField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRDoubleField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRDouble::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRDouble::class.java)
                val field = GDoRDoubleField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRBooleanField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRBoolean::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRBoolean::class.java)
                val field = GDoRBooleanField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRCharField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRChar::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRChar::class.java)
                val field = GDoRCharField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun processGDoRStringField(
        roundEnvironment: RoundEnvironment, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(GDoRString::class.java)
            .filter {
                SuperficialValidation.validateElement(it)
            }.forEach {
                val className = getClassName(it.enclosingElement)
                val elementName = it.simpleName.toString()
                val dor = it.getAnnotation(GDoRString::class.java)
                val field = GDoRStringField(elementName, dor.debug, dor.release)
                if (fields[className] == null) {
                    fields[className] = ArrayList()
                }
                fields[className]?.add(field)
            }
    }

    private fun getClassName(element: Element): String {
        val packageName = processingEnv.elementUtils.getPackageOf(element).qualifiedName.toString()
        val elementName = element.simpleName
        return "${packageName}.${elementName}"
    }

    private fun generatePROJECT_GDoRClass(projectName: String, fields: HashMap<String, ArrayList<GDoRField<*>>>) {
        val javaFile = JavaFile
            .builder(GConstants.GADGET_GENERATE_PACKAGE, getPROJECT_GDoRTypeSpec(projectName, fields))
            .build()
        try {
            javaFile.writeTo(processingEnv.filer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getPROJECT_GDoRTypeSpec(projectName: String, fields: HashMap<String, ArrayList<GDoRField<*>>>): TypeSpec {
        val className = "${projectName.replace("-", "").toUpperCase()}_TaGDoR"
        return TypeSpec.classBuilder(className)
            .addSuperinterface(TaGDoR::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addMethod(getInitMethodSpec(fields))
            .build()
    }

    private fun getInitMethodSpec(fields: HashMap<String, ArrayList<GDoRField<*>>>): MethodSpec {
        val methodSpecBuilder = MethodSpec.methodBuilder("init")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(Boolean::class.java, "isDebug")
            .beginControlFlow("if (isDebug)")
        fields.forEach { (className, fieldList) ->
            fieldList.forEach { field ->
                when (field) {
                    is GDoRLongField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = ${field.debug}L")
                    is GDoRFloatField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = ${field.debug}F")
                    is GDoRCharField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = \'${field.debug}\'")
                    is GDoRStringField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = \"${field.debug}\"")
                    else ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = ${field.debug}")
                }
            }
        }
        methodSpecBuilder.nextControlFlow("else")
        fields.forEach { (className, fieldList) ->
            fieldList.forEach { field ->
                when (field) {
                    is GDoRLongField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = ${field.release}L")
                    is GDoRFloatField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = ${field.release}F")
                    is GDoRCharField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = \'${field.release}\'")
                    is GDoRStringField ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = \"${field.release}\"")
                    else ->
                        methodSpecBuilder.addStatement("${className}.${field.name} = ${field.release}")
                }
            }
        }
        methodSpecBuilder.endControlFlow()
        return methodSpecBuilder.build()
    }
}