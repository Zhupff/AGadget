package gadget.common.model

import java.io.Serializable

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */


/**
 * Represents a tuple of 2 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut2Tuple] exhibits value semantics, i.e. two [GMut2Tuple] are equal if both components are equal.
 */
data class GMut2Tuple<First, Second>(
    var first: First,
    var second: Second
) : Serializable {

    fun toG2Tuple() = G2Tuple(first, second)

    override fun toString(): String = "(${first}, ${second})"
}

/**
 * Converts into a list.
 */
fun <T> GMut2Tuple<T, T>.toList() = mutableListOf(first, second)

/**
 * Converts into a set.
 */
fun <T> GMut2Tuple<T, T>.toSet() = mutableSetOf(first, second)

/**
 * Converts into a map.
 */
fun <K, V> GMut2Tuple<K, V>.toMap() = mutableMapOf(first to second)


/**
 * Represents a tuple of 3 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut3Tuple] exhibits value semantics, i.e. two [GMut3Tuple] are equal if both components are equal.
 */
data class GMut3Tuple<First, Second, Third>(
    var first: First,
    var second: Second,
    var third: Third
) : Serializable {

    fun toG3Tuple() = G3Tuple(first, second, third)

    override fun toString(): String = "(${first}, ${second}, ${third})"
}

/**
 * Converts into a list.
 */
fun <T> GMut3Tuple<T, T, T>.toList() = mutableListOf(first, second, third)

/**
 * Converts into a set.
 */
fun <T> GMut3Tuple<T, T, T>.toSet() = mutableSetOf(first, second, third)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> GMut3Tuple<K, V, T>.toMap() = mutableMapOf(first to second)


/**
 * Represents a tuple of 4 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut4Tuple] exhibits value semantics, i.e. two [GMut4Tuple] are equal if both components are equal.
 */
data class GMut4Tuple<First, Second, Third, Fourth>(
    var first: First,
    var second: Second,
    var third: Third,
    var fourth: Fourth
) : Serializable {

    fun toG4Tuple() = G4Tuple(first, second, third, fourth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth})"
}

/**
 * Converts into a list.
 */
fun <T> GMut4Tuple<T, T, T, T>.toList() = mutableListOf(first, second, third, fourth)

/**
 * Converts into a set.
 */
fun <T> GMut4Tuple<T, T, T, T>.toSet() = mutableSetOf(first, second, third, fourth)

/**
 * Converts into a map.
 */
fun <K, V> GMut4Tuple<K, V, K, V>.toMap() = mutableMapOf(first to second, third to fourth)


/**
 * Represents a tuple of 5 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut5Tuple] exhibits value semantics, i.e. two [GMut5Tuple] are equal if both components are equal.
 */
data class GMut5Tuple<First, Second, Third, Fourth, Fifth>(
    var first: First,
    var second: Second,
    var third: Third,
    var fourth: Fourth,
    var fifth: Fifth
) : Serializable {

    fun toG5Tuple() = G5Tuple(first, second, third, fourth, fifth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth})"
}

/**
 * Converts into a list.
 */
fun <T> GMut5Tuple<T, T, T, T, T>.toList() = mutableListOf(first, second, third, fourth, fifth)

/**
 * Converts into a set.
 */
fun <T> GMut5Tuple<T, T, T, T, T>.toSet() = mutableSetOf(first, second, third, fourth, fifth)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> GMut5Tuple<K, V, K, V, T>.toMap() = mutableMapOf(first to second, third to fourth)


/**
 * Represents a tuple of 6 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut6Tuple] exhibits value semantics, i.e. two [GMut6Tuple] are equal if both components are equal.
 */
data class GMut6Tuple<First, Second, Third, Fourth, Fifth, Sixth>(
    var first: First,
    var second: Second,
    var third: Third,
    var fourth: Fourth,
    var fifth: Fifth,
    var sixth: Sixth
) : Serializable {

    fun toG6Tuple() = G6Tuple(first, second, third, fourth, fifth, sixth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth})"
}

/**
 * Converts into a list.
 */
fun <T> GMut6Tuple<T, T, T, T, T, T>.toList() = mutableListOf(first, second, third, fourth, fifth, sixth)

/**
 * Converts into a set.
 */
fun <T> GMut6Tuple<T, T, T, T, T, T>.toSet() = mutableSetOf(first, second, third, fourth, fifth, sixth)

/**
 * Converts into a map.
 */
fun <K, V> GMut6Tuple<K, V, K, V, K, V>.toMap() = mutableMapOf(first to second, third to fourth, fifth to sixth)


/**
 * Represents a tuple of 7 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut7Tuple] exhibits value semantics, i.e. two [GMut7Tuple] are equal if both components are equal.
 */
data class GMut7Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh>(
    var first: First,
    var second: Second,
    var third: Third,
    var fourth: Fourth,
    var fifth: Fifth,
    var sixth: Sixth,
    var seventh: Seventh
) : Serializable {

    fun toG7Tuple() = G7Tuple(first, second, third, fourth, fifth, sixth, seventh)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth}, ${seventh})"
}

/**
 * Converts into a list.
 */
fun <T> GMut7Tuple<T, T, T, T, T, T, T>.toList() = mutableListOf(first, second, third, fourth, fifth, sixth, seventh)

/**
 * Converts into a set.
 */
fun <T> GMut7Tuple<T, T, T, T, T, T, T>.toSet() = mutableSetOf(first, second, third, fourth, fifth, sixth, seventh)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> GMut7Tuple<K, V, K, V, K, V, T>.toMap() = mutableMapOf(first to second, third to fourth, fifth to sixth)


/**
 * Represents a tuple of 8 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut8Tuple] exhibits value semantics, i.e. two [GMut8Tuple] are equal if both components are equal.
 */
data class GMut8Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>(
    var first: First,
    var second: Second,
    var third: Third,
    var fourth: Fourth,
    var fifth: Fifth,
    var sixth: Sixth,
    var seventh: Seventh,
    var eighth: Eighth
) : Serializable {

    fun toG8Tuple() = G8Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth}, ${seventh}, ${eighth})"
}

/**
 * Converts into a list.
 */
fun <T> GMut8Tuple<T, T, T, T, T, T, T, T>.toList() = mutableListOf(first, second, third, fourth, fifth, sixth, seventh, eighth)

/**
 * Converts into a set.
 */
fun <T> GMut8Tuple<T, T, T, T, T, T, T, T>.toSet() = mutableSetOf(first, second, third, fourth, fifth, sixth, seventh, eighth)

/**
 * Converts into a map.
 */
fun <K, V> GMut8Tuple<K, V, K, V, K, V, K, V>.toMap() = mutableMapOf(first to second, third to fourth, fifth to sixth, seventh to eighth)


/**
 * Represents a tuple of 9 values.
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * [GMut9Tuple] exhibits value semantics, i.e. two [GMut9Tuple] are equal if both components are equal.
 */
data class GMut9Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>(
    var first: First,
    var second: Second,
    var third: Third,
    var fourth: Fourth,
    var fifth: Fifth,
    var sixth: Sixth,
    var seventh: Seventh,
    var eighth: Eighth,
    var ninth: Ninth
) : Serializable {

    fun toG9Tuple() = G9Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)

    override fun toString(): String = "(${first}, ${second}, ${third}, ${fourth}, ${fifth}, ${sixth}, ${seventh}, ${eighth}, ${ninth})"
}

/**
 * Converts into a list.
 */
fun <T> GMut9Tuple<T, T, T, T, T, T, T, T, T>.toList() = mutableListOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)

/**
 * Converts into a set.
 */
fun <T> GMut9Tuple<T, T, T, T, T, T, T, T, T>.toSet() = mutableSetOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)

/**
 * Converts into a map. The last one will be lost.
 */
fun <K, V, T> GMut9Tuple<K, V, K, V, K, V, K, V, T>.toMap() = mutableMapOf(first to second, third to fourth, fifth to sixth, seventh to eighth)