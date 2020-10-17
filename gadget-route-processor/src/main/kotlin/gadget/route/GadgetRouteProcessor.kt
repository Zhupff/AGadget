package gadget.route

import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import gadget.base.processor.BaseGadgetProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import kotlin.collections.HashMap

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
@AutoService(Processor::class)
class GadgetRouteProcessor : BaseGadgetProcessor() {

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(G_Route::class.java.canonicalName)

    override fun process(typeElementSet: Set<TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        if (typeElementSet == null) {
            return false
        }
        val moduleName = processingEnv.options["GADGET_ROUTE"]
        if (moduleName == null || moduleName.trim().isEmpty()) {
            return false
        }

        val routeTable = HashMap<String, String>()
        roundEnvironment?.getElementsAnnotatedWith(G_Route::class.java)?.forEach { element ->
            val packageName = processingEnv.elementUtils.getPackageOf(element).qualifiedName.toString()
            val elementName = element.simpleName
            val route = element.getAnnotation(G_Route::class.java)
            val path = route.path
            routeTable[path] = "$packageName.$elementName"
        }
        if (routeTable.isNullOrEmpty()) {
            return false
        }

        val javaFile = JavaFile.builder("gadget.route", getXxxRouteTableTypeSpec(moduleName, routeTable)).build()
        try {
            javaFile.writeTo(processingEnv.filer)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    private fun getXxxRouteTableTypeSpec(moduleName: String, routeTable: HashMap<String, String>): TypeSpec {
        val mName = moduleName.toUpperCase().replace("-", "")
        return TypeSpec.classBuilder("${mName}_RouteTable")
            .superclass(G_RouteTable::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addMethod(getRegisterMethodSpec(routeTable))
            .build()
    }

    private fun getRegisterMethodSpec(routeTable: HashMap<String, String>): MethodSpec {
        val methodSpecBuilder = MethodSpec.methodBuilder("register")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(HashMap::class.java, "routeMap")
        routeTable.forEach { (path, route) ->
            methodSpecBuilder.addStatement("routeMap.put(\$S, \$S)", path, route)
        }
        return methodSpecBuilder.build()
    }
}