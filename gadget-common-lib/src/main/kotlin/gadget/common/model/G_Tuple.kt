package gadget.common.model

import java.io.Serializable

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */

/**
 * Represents a single value.
 * There is no meaning attached to value in this class, it can be used for any purpose.
 * [G_Single] exhibits value semantics, i.e. two [G_Single] are equal if both component are equal.
 */
data class G_Single<A>(
    val first: A
) : Serializable {
    override fun toString(): String = "($first)"
}

/**
 * Converts into a list.
 */
fun <T> G_Single<T>.toList(): List<T> = listOf(first)


/**
 * Represents a tuple of two values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_Double] exhibits value semantics, i.e. two [G_Double] are equal if both components are equal.
 */
data class G_Double<A, B>(
    val first: A,
    val second: B
) : Serializable {
    override fun toString(): String = "($first, $second)"
}

/**
 * Converts into a list.
 */
fun <T> G_Double<T, T>.toList(): List<T> = listOf(first, second)

/**
 * Converts into a map.
 */
fun <A, B> G_Double<A, B>.toMap(): Map<A, B> = mapOf(first to second)


/**
 * Represents a tuple of three values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_Trible] exhibits value semantics, i.e. two [G_Trible] are equal if both components are equal.
 */
data class G_Trible<A, B, C>(
    val first: A,
    val second: B,
    val third: C
) : Serializable {
    override fun toString(): String = "($first, $second, $third)"
}

/**
 * Converts into a list.
 */
fun <T> G_Trible<T, T, T>.toList(): List<T> = listOf(first, second, third)

/**
 * Converts into a map, and it will lose the last value.
 */
fun <A, B, T> G_Trible<A, B, T>.toMap(): Map<A, B> = mapOf(first to second)


/**
 * Represents a tuple of four values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_Quadra] exhibits value semantics, i.e. two [G_Quadra] are equal if both components are equal.
 */
data class G_Quadra<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}

/**
 * Converts into a list.
 */
fun <T> G_Quadra<T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth)

/**
 * Converts into a map.
 */
fun <A, B> G_Quadra<A, B, A, B>.toList(): Map<A, B> = mapOf(first to second, third to fourth)


/**
 * Represents a tuple of five values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_Penta] exhibits value semantics, i.e. two [G_Penta] are equal if both components are equal.
 */
data class G_Penta<A, B, C, D, E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
) : Serializable {
    override fun toString(): String = "($first, $second, $third, $fourth, $fifth)"
}

/**
 * Converts into a list.
 */
fun <T> G_Penta<T, T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth, fifth)

/**
 * Converts into a map, and it will lose the last value.
 */
fun <A, B, T> G_Penta<A, B, A, B, T>.toMap(): Map<A, B> = mapOf(first to second, third to fourth)