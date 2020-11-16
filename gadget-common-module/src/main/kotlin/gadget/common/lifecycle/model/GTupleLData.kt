package gadget.common.lifecycle.model

import gadget.common.model.*

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */


class G2TupleLData<First, Second>
    : GLiveData<G2Tuple<First, Second>> {

    constructor(tuple: G2Tuple<First, Second>)
        : super(tuple)

    constructor(first: First, second: Second)
        : super(G2Tuple(first, second))
}


class G3TupleLData<First, Second, Third>
    : GLiveData<G3Tuple<First, Second, Third>> {

    constructor(tuple: G3Tuple<First, Second, Third>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third)
        : super(G3Tuple(first, second, third))
}


class G4TupleLData<First, Second, Third, Fourth>
    : GLiveData<G4Tuple<First, Second, Third, Fourth>> {

    constructor(tuple: G4Tuple<First, Second, Third, Fourth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth)
        : super(G4Tuple(first, second, third, fourth))
}


class G5TupleLData<First, Second, Third, Fourth, Fifth>
    : GLiveData<G5Tuple<First, Second, Third, Fourth, Fifth>> {

    constructor(tuple: G5Tuple<First, Second, Third, Fourth, Fifth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth, fifth: Fifth)
        : super(G5Tuple(first, second, third, fourth, fifth))
}


class G6TupleLData<First, Second, Third, Fourth, Fifth, Sixth>
    : GLiveData<G6Tuple<First, Second, Third, Fourth, Fifth, Sixth>> {

    constructor(tuple: G6Tuple<First, Second, Third, Fourth, Fifth, Sixth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth)
        : super(G6Tuple(first, second, third, fourth, fifth, sixth))
}


class G7TupleLData<First, Second, Third, Fourth, Fifth, Sixth, Seventh>
    : GLiveData<G7Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh>> {

    constructor(tuple: G7Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth, seventh: Seventh)
        : super(G7Tuple(first, second, third, fourth, fifth, sixth, seventh))
}


class G8TupleLData<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>
    : GLiveData<G8Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>> {

    constructor(tuple: G8Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth, seventh: Seventh, eighth: Eighth)
        : super(G8Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth))
}


class G9TupleLData<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>
    : GLiveData<G9Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>> {

    constructor(tuple: G9Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth, seventh: Seventh, eighth: Eighth, ninth: Ninth)
        : super(G9Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth))
}