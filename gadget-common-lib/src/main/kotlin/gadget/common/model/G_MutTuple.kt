package gadget.common.model

import java.io.Serializable

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */

/**
 * Represents a single value.
 * There is no meaning attached to value in this class, it can be used for any purpose.
 * [G_MutSingle] exhibits value semantics, i.e. two [G_MutSingle] are equal if both component are equal.
 */
data class G_MutSingle<A>(
    var first: A
) : Serializable {
    override fun toString(): String = "($first)"
}

/**
 * Converts into a list.
 */
fun <T> G_MutSingle<T>.toList(): List<T> = mutableListOf(first)


/**
 * Represents a tuple of two values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_MutDouble] exhibits value semantics, i.e. two [G_MutDouble] are equal if both components are equal.
 */
data class G_MutDouble<A, B>(
    var first: A,
    var second: B
) : Serializable {
    override fun toString(): String = "($first, $second)"
}

/**
 * Converts into a list.
 */
fun <T> G_MutDouble<T, T>.toList(): List<T> = mutableListOf(first, second)

/**
 * Converts into a map.
 */
fun <A, B> G_MutDouble<A, B>.toMap(): Map<A, B> = mutableMapOf(first to second)


/**
 * Represents a tuple of three values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_MutTrible] exhibits value semantics, i.e. two [G_MutTrible] are equal if both components are equal.
 */
data class G_MutTrible<A, B, C>(
    var first: A,
    var second: B,
    var third: C
) : Serializable {
    override fun toString(): String = "($first, $second, $third)"
}

/**
 * Converts into a list.
 */
fun <T> G_MutTrible<T, T, T>.toList(): List<T> = mutableListOf(first, second, third)

/**
 * Converts into a map, and it will lose the last value.
 */
fun <A, B, T> G_MutTrible<A, B, T>.toMap(): Map<A, B> = mutableMapOf(first to second)


/**
 * Represents a tuple of four values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_MutQuadra] exhibits value semantics, i.e. two [G_MutQuadra] are equal if both components are equal.
 */
data class G_MutQuadra<A, B, C, D>(
    var first: A,
    var second: B,
    var third: C,
    var fourth: D
) : Serializable {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}

/**
 * Converts into a list.
 */
fun <T> G_MutQuadra<T, T, T, T>.toList(): List<T> = mutableListOf(first, second, third, fourth)

/**
 * Converts into a map.
 */
fun <A, B> G_MutQuadra<A, B, A, B>.toList(): Map<A, B> = mutableMapOf(first to second, third to fourth)


/**
 * Represents a tuple of five values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G_MutPenta] exhibits value semantics, i.e. two [G_MutPenta] are equal if both components are equal.
 */
data class G_MutPenta<A, B, C, D, E>(
    var first: A,
    var second: B,
    var third: C,
    var fourth: D,
    var fifth: E
) : Serializable {
    override fun toString(): String = "($first, $second, $third, $fourth, $fifth)"
}

/**
 * Converts into a list.
 */
fun <T> G_MutPenta<T, T, T, T, T>.toList(): List<T> = mutableListOf(first, second, third, fourth, fifth)

/**
 * Converts into a map, and it will lose the last value.
 */
fun <A, B, T> G_MutPenta<A, B, A, B, T>.toMap(): Map<A, B> = mutableMapOf(first to second, third to fourth)