package gadget.common.lifecycle.model

import gadget.common.model.*

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */


class GMut2TupleLData<First, Second>
    : GLiveData<GMut2Tuple<First, Second>> {

    constructor(tuple: GMut2Tuple<First, Second>)
        : super(tuple)

    constructor(first: First, second: Second)
        : super(GMut2Tuple(first, second))
}


class GMut3TupleLData<First, Second, Third>
    : GLiveData<GMut3Tuple<First, Second, Third>> {

    constructor(tuple: GMut3Tuple<First, Second, Third>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third)
        : super(GMut3Tuple(first, second, third))
}


class GMut4TupleLData<First, Second, Third, Fourth>
    : GLiveData<GMut4Tuple<First, Second, Third, Fourth>> {

    constructor(tuple: GMut4Tuple<First, Second, Third, Fourth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth)
        : super(GMut4Tuple(first, second, third, fourth))
}


class GMut5TupleLData<First, Second, Third, Fourth, Fifth>
    : GLiveData<GMut5Tuple<First, Second, Third, Fourth, Fifth>> {

    constructor(tuple: GMut5Tuple<First, Second, Third, Fourth, Fifth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth, fifth: Fifth)
        : super(GMut5Tuple(first, second, third, fourth, fifth))
}


class GMut6TupleLData<First, Second, Third, Fourth, Fifth, Sixth>
    : GLiveData<GMut6Tuple<First, Second, Third, Fourth, Fifth, Sixth>> {

    constructor(tuple: GMut6Tuple<First, Second, Third, Fourth, Fifth, Sixth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth)
        : super(GMut6Tuple(first, second, third, fourth, fifth, sixth))
}


class GMut7TupleLData<First, Second, Third, Fourth, Fifth, Sixth, Seventh>
    : GLiveData<GMut7Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh>> {

    constructor(tuple: GMut7Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth, seventh: Seventh)
        : super(GMut7Tuple(first, second, third, fourth, fifth, sixth, seventh))
}


class GMut8TupleLData<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>
    : GLiveData<GMut8Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>> {

    constructor(tuple: GMut8Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth, seventh: Seventh, eighth: Eighth)
        : super(GMut8Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth))
}


class GMut9TupleLData<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>
    : GLiveData<GMut9Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>> {

    constructor(tuple: GMut9Tuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth>)
        : super(tuple)

    constructor(first: First, second: Second, third: Third, fourth: Fourth,
                fifth: Fifth, sixth: Sixth, seventh: Seventh, eighth: Eighth, ninth: Ninth)
        : super(GMut9Tuple(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth))
}