package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class G_BooleanGoR(val debug: Boolean, val release: Boolean)