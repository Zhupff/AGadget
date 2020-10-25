package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
interface FieldDoR<T> {
    val name: String
    val debug: T
    val release: T
}