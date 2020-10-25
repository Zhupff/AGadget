package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class G_DoubleDoR(val debug: Double, val release: Double)