package gadget.common.util

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
object GList {

    /**
     * If the number of elements in the list is odd, the last element will be lost.
     */
    fun <T> toMap(list: List<T>): Map<T, T> {
        val map = HashMap<T, T>()
        for (i in list.indices step 2) {
            val j = i + 1
            if (j < list.size) {
                map[list[i]] = list[j]
            }
        }
        return map
    }

    /**
     * If the number of elements in the list is odd, the last element will be lost.
     */
    fun <T> toMutMap(list: List<T>): Map<T, T> {
        val map = HashMap<T, T>()
        for (i in list.indices step 2) {
            val j = i + 1
            if (j < list.size) {
                map[list[i]] = list[j]
            }
        }
        return map
    }

    /**
     * If the number of elements in the list is odd, the last element will be lost.
     */
    fun <T> toMapWithStringKey(list: List<T>): Map<String, T> {
        val map = HashMap<String, T>()
        for (i in list.indices step 2) {
            val j = i + 1
            if (j < list.size) {
                map[list[i].toString()] = list[j]
            }
        }
        return map
    }

    /**
     * If the number of elements in the list is odd, the last element will be lost.
     */
    fun <T> toMutMapWithStringKey(list: List<T>): Map<String, T> {
        val map = HashMap<String, T>()
        for (i in list.indices step 2) {
            val j = i + 1
            if (j < list.size) {
                map[list[i].toString()] = list[j]
            }
        }
        return map
    }
}