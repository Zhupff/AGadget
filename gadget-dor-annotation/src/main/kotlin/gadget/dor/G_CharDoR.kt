package gadget.dor

import kotlin.Char

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class G_CharDoR(val debug: Char, val release: Char, val beFinal: Boolean = false)