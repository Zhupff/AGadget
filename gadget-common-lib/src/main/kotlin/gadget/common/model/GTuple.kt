package gadget.common.model

import java.io.Serializable

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */


/**
 * Represents a tuple of 2 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G2Tuple] exhibits value semantics, i.e. two [G2Tuple] are equal if both components are equal.
 */
data class G2Tuple<First, Second>(
    val first: First,
    val second: Second
) : Serializable {

    fun toGMut2Tuple() = GMut2Tuple(first, second)

    override fun toString(): String = "(${first}, ${second})"
}

/**
 * Converts into a list.
 */
fun <T> G2Tuple<T, T>.toList() = listOf(first, second)

/**
 * Converts into a set.
 */
fun <T> G2Tuple<T, T>.toSet() = setOf(first, second)

/**
 * Converts into a map.
 */
fun <K, V> G2Tuple<K, V>.toMap() = mapOf(first to second)

/**
 * Represents a tuple of 3 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G3Tuple] exhibits value semantics, i.e. two [G3Tuple] are equal if both components are equal.
 */
data class G3Tuple<First, Second, Third>(
    val first: First,
    val second: Second,
    val third: Third
) : Serializable {

    fun toGMut3Tuple() = GMut3Tuple(first, second, third)

    override fun toString(): String = "(${first}, ${second}, ${third})"
}

/**
 * Converts into a list.
 */
fun <T> G3Tuple<T, T, T>.toList() = listOf(first, second, third)

/**
 * Converts into a set.
 */
fun <T> G3Tuple<T, T, T>.toSet() = setOf(first, second, third)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> G3Tuple<K, V, T>.toMap() = mapOf(first to second)


/**
 * Represents a tuple of 4 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G4Tuple] exhibits value semantics, i.e. two [G4Tuple] are equal if both components are equal.
 */
data class G4Tuple<First, Second, Third, Fourth>(
    val first: First,
    val second: Second,
    val third: Third,
    val fourth: Fourth
) : Serializable {

    fun toGMut4Tuple() = GMut4Tuple(first, second, third, fourth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth})"
}

/**
 * Converts into a list.
 */
fun <T> G4Tuple<T, T, T, T>.toList() = listOf(first, second, third, fourth)

/**
 * Converts into a set.
 */
fun <T> G4Tuple<T, T, T, T>.toSet() = setOf(first, second, third, fourth)

/**
 * Converts into a map.
 */
fun <K, V> G4Tuple<K, V, K, V>.toMap() = mapOf(first to second, third to fourth)


/**
 * Represents a tuple of 5 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G5Tuple] exhibits value semantics, i.e. two [G5Tuple] are equal if both components are equal.
 */
data class G5Tuple<First, Second, Third, Fourth, Fifth>(
    val first: First,
    val second: Second,
    val third: Third,
    val fourth: Fourth,
    val fifth: Fifth
) : Serializable {

    fun toGMut5Tuple() = GMut5Tuple(first, second, third, fourth, fifth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth})"
}

/**
 * Converts into a list.
 */
fun <T> G5Tuple<T, T, T, T, T>.toList() = listOf(first, second, third, fourth, fifth)

/**
 * Converts into a set.
 */
fun <T> G5Tuple<T, T, T, T, T>.toSet() = setOf(first, second, third, fourth, fifth)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> G5Tuple<K, V, K, V, T>.toMap() = mapOf(first to second, third to fourth)


/**
 * Represents a tuple of 6 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G6Tuple] exhibits value semantics, i.e. two [G6Tuple] are equal if both components are equal.
 */
data class G6Tuple<First, Second, Third, Fourth, Fifth, Sixth>(
    val first: First,
    val second: Second,
    val third: Third,
    val fourth: Fourth,
    val fifth: Fifth,
    val sixth: Sixth
) : Serializable {

    fun toGMut6Tuple() = GMut6Tuple(first, second, third, fourth, fifth, sixth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth})"
}

/**
 * Converts into a list.
 */
fun <T> G6Tuple<T, T, T, T, T, T>.toList() = listOf(first, second, third, fourth, fifth, sixth)

/**
 * Converts into a set.
 */
fun <T> G6Tuple<T, T, T, T, T, T>.toSet() = setOf(first, second, third, fourth, fifth, sixth)

/**
 * Converts into a map.
 */
fun <K, V> G6Tuple<K, V, K, V, K, V>.toMap() = mapOf(first to second, third to fourth, fifth to sixth)


/**
 * Represents a tuple of 7 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G7Tuple] exhibits value semantics, i.e. two [G7Tuple] are equal if both components are equal.
 */
data class G7Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh>(
    val first: First,
    val second: Second,
    val third: Third,
    val fourth: Fourth,
    val fifth: Fifth,
    val sixth: Sixth,
    val seventh: Seventh
) : Serializable {

    fun toGMut7Tuple() = GMut7Tuple(first, second, third, fourth, fifth, sixth, seventh)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth}, ${seventh})"
}

/**
 * Converts into a list.
 */
fun <T> G7Tuple<T, T, T, T, T, T, T>.toList() = listOf(first, second, third, fourth, fifth, sixth, seventh)

/**
 * Converts into a set.
 */
fun <T> G7Tuple<T, T, T, T, T, T, T>.toSet() = setOf(first, second, third, fourth, fifth, sixth, seventh)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> G7Tuple<K, V, K, V, K, V, T>.toMap() = mapOf(first to second, third to fourth, fifth to sixth)


/**
 * Represents a tuple of 8 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G8Tuple] exhibits value semantics, i.e. two [G8Tuple] are equal if both components are equal.
 */
data class G8Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>(
    val first: First,
    val second: Second,
    val third: Third,
    val fourth: Fourth,
    val fifth: Fifth,
    val sixth: Sixth,
    val seventh: Seventh,
    val eighth: Eighth
) : Serializable {

    fun toGMut8Tuple() = GMut8Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth}, ${seventh}, ${eighth})"
}

/**
 * Converts into a list.
 */
fun <T> G8Tuple<T, T, T, T, T, T, T, T>.toList() = listOf(first, second, third, fourth, fifth, sixth, seventh, eighth)

/**
 * Converts into a set.
 */
fun <T> G8Tuple<T, T, T, T, T, T, T, T>.toSet() = setOf(first, second, third, fourth, fifth, sixth, seventh, eighth)

/**
 * Converts into a map.
 */
fun <K, V> G8Tuple<K, V, K, V, K, V, K, V>.toMap() = mapOf(first to second, third to fourth, fifth to sixth, seventh to eighth)


/**
 * Represents a tuple of 9 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [G9Tuple] exhibits value semantics, i.e. two [G9Tuple] are equal if both components are equal.
 */
data class G9Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>(
    val first: First,
    val second: Second,
    val third: Third,
    val fourth: Fourth,
    val fifth: Fifth,
    val sixth: Sixth,
    val seventh: Seventh,
    val eighth: Eighth,
    val ninth: Ninth
) : Serializable {

    fun toGMut9Tuple() = GMut9Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth}, ${seventh}, ${eighth}, ${ninth})"
}

/**
 * Converts into a list.
 */
fun <T> G9Tuple<T, T, T, T, T, T, T, T, T>.toList() = listOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)

/**
 * Converts into a set.
 */
fun <T> G9Tuple<T, T, T, T, T, T, T, T, T>.toSet() = setOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> G9Tuple<K, V, K, V, K, V, K, V, T>.toMap() = mapOf(first to second, third to fourth, fifth to sixth, seventh to eighth)