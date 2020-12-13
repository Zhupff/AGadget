package gadget.route.processor

import com.google.auto.common.SuperficialValidation
import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import gadget.base.processor.GAnnotationProcessor
import gadget.common.GConstants
import gadget.common.util.GString
import gadget.route.GRouteConstants
import gadget.route.GRoutePath
import gadget.route.GRouteTable
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
@AutoService(Processor::class)
class GRoutePathAnnotationProcessor : GAnnotationProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> = mutableSetOf(GRoutePath::class.java.canonicalName)

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        if (typeElementSet == null || roundEnvironment == null) {
            return false
        }
        val projectName = processingEnv.options[GConstants.COMPILE_OPTION_PROJECT_NAME]
        if (GString.isNullOrEmpty(projectName)) {
            return false
        }

        val routeTable = HashMap<String, String>()
        roundEnvironment.getElementsAnnotatedWith(GRoutePath::class.java)
            .filter { SuperficialValidation.validateElement(it) }
            .forEach {
                val packageName = processingEnv.elementUtils.getPackageOf(it).qualifiedName.toString()
                val elementName = it.simpleName.toString()
                val route = it.getAnnotation(GRoutePath::class.java).route
                routeTable[route] = "${packageName}.${elementName}"
            }
        if (routeTable.isNullOrEmpty()) {
            return false
        }

        val javaFile = JavaFile
            .builder(GConstants.GADGET_GENERATE_PACKAGE, getPROJECT_GROUTETABLETypeSpec(projectName!!, routeTable))
            .build()
        try {
            javaFile.writeTo(processingEnv.filer)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    private fun getPROJECT_GROUTETABLETypeSpec(projectName: String, routeTable: HashMap<String, String>): TypeSpec {
        val className = "${projectName.replace("-", "").toUpperCase()}${GRouteConstants.GROUTETABLE_SUFFIX}"
        return TypeSpec.classBuilder(className)
            .addSuperinterface(GRouteTable::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addMethod(getInitMethodSpec(routeTable))
            .build()
    }

    private fun getInitMethodSpec(routeTable: HashMap<String, String>): MethodSpec {
        val methodSpecBuilder = MethodSpec.methodBuilder("init")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(MutableMap::class.java, "routeTable")
        routeTable.forEach { (route, path) ->
            methodSpecBuilder.addStatement("routeTable.put(\"${route}\", \"${path}\")")
        }
        return methodSpecBuilder.build()
    }
}