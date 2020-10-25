package gadget.dor

import com.google.auto.common.SuperficialValidation
import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
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
class GadgetDoRProcessor : BaseGadgetProcessor() {

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(
        G_ByteDoR::class.java.canonicalName,
        G_ShortDoR::class.java.canonicalName,
        G_IntDoR::class.java.canonicalName,
        G_LongDoR::class.java.canonicalName,
        G_FloatDoR::class.java.canonicalName,
        G_DoubleDoR::class.java.canonicalName,
        G_BooleanGoR::class.java.canonicalName,
        G_CharDoR::class.java.canonicalName,
        G_StringDoR::class.java.canonicalName
    )

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        if (typeElementSet == null || roundEnvironment == null) {
            return false
        }

        val fieldTable = HashMap<String, ArrayList<FieldDoR<*>>>()
        processG_ByteDoR(roundEnvironment, fieldTable)
        processG_ShortDoR(roundEnvironment, fieldTable)
        processG_IntDoR(roundEnvironment, fieldTable)
        processG_LongDoR(roundEnvironment, fieldTable)
        processG_FloatDoR(roundEnvironment, fieldTable)
        processG_DoubleDoR(roundEnvironment, fieldTable)
        processG_BooleanDoR(roundEnvironment, fieldTable)
        processG_CharDoR(roundEnvironment, fieldTable)
        processG_StringDoR(roundEnvironment, fieldTable)

        fieldTable.forEach { (classPath, fields) ->
            generateXxx_DoRClass(classPath, fields)
        }
        return true
    }

    private fun processG_ByteDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_ByteDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_ByteDoR::class.java)
                val fieldDoR = ByteFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_ShortDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_ShortDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_ShortDoR::class.java)
                val fieldDoR = ShortFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_IntDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_IntDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_IntDoR::class.java)
                val fieldDoR = IntFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_LongDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_LongDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_LongDoR::class.java)
                val fieldDoR = LongFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_FloatDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_FloatDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_FloatDoR::class.java)
                val fieldDoR = FloatFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_DoubleDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_DoubleDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_DoubleDoR::class.java)
                val fieldDoR = DoubleFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_BooleanDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_BooleanGoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_BooleanGoR::class.java)
                val fieldDoR = BooleanFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_CharDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_CharDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_CharDoR::class.java)
                val fieldDoR = CharFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun processG_StringDoR(roundEnvironment: RoundEnvironment, fieldTable: HashMap<String, ArrayList<FieldDoR<*>>>) {
        roundEnvironment.getElementsAnnotatedWith(G_StringDoR::class.java)
            .filter { element ->
                SuperficialValidation.validateElement(element)
            }.forEach { element ->
                val classPath = getClassPath(element.enclosingElement)
                val elementName = element.simpleName.toString()
                val dor = element.getAnnotation(G_StringDoR::class.java)
                val fieldDoR = StringFieldDoR(elementName, dor.debug, dor.release)
                if (fieldTable[classPath] == null) {
                    fieldTable[classPath] = ArrayList()
                }
                fieldTable[classPath]?.add(fieldDoR)
            }
    }

    private fun getClassPath(element: Element): String {
        val packageName = processingEnv.elementUtils.getPackageOf(element).qualifiedName.toString()
        val elementName = element.simpleName
        return "${packageName}=${elementName}"
    }

    private fun generateXxx_DoRClass(classPath: String, fields: List<FieldDoR<*>>) {
        val strList = classPath.split("=")
        if (strList.size != 2) {
            return
        }
        val classPackage = strList[0]
        val className = strList[1]
        val javaFile = JavaFile.builder(classPackage, getXxx_DoRTypeSpec(className, fields)).build()
        try {
            javaFile.writeTo(processingEnv.filer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getXxx_DoRTypeSpec(className: String, fields: List<FieldDoR<*>>): TypeSpec {
        return TypeSpec.classBuilder("${className}_DoR")
            .addSuperinterface(TaG_DoR::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addMethod(getInitMethodSpec(className, fields))
            .build()
    }

    private fun getInitMethodSpec(className: String, fields: List<FieldDoR<*>>): MethodSpec {
        val methodSpecBuilder = MethodSpec.methodBuilder("init")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(Boolean::class.java, "isDebug")
            .beginControlFlow("if (isDebug)")
        fields.forEach { field ->
            when (field) {
                is LongFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = ${field.debug}L")
                is FloatFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = ${field.debug}F")
                is CharFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = \'${field.debug}\'")
                is StringFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = \"${field.debug}\"")
                else ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = ${field.debug}")
            }
        }
        methodSpecBuilder.nextControlFlow("else")
        fields.forEach { field ->
            when (field) {
                is LongFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = ${field.release}L")
                is FloatFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = ${field.release}F")
                is CharFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = \'${field.release}\'")
                is StringFieldDoR ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = \"${field.release}\"")
                else ->
                    methodSpecBuilder.addStatement("${className}.${field.name} = ${field.release}")
            }
        }
        methodSpecBuilder.endControlFlow()
        return methodSpecBuilder.build()
    }
}