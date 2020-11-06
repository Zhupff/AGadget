package gadget.common

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object G_String {

    fun isNullOrEmpty(s: String?) = s == null || s.trim().isEmpty()

    fun isNotNullNorEmpty(s: String?) = s != null && s.trim().isNotEmpty()

    fun areNotNullAndEqual(s1: String?, s2: String?) = s1 != null && s1 == s2

    fun areNotNullAndUnequal(s1: String?, s2: String?) = s1 != null && s2 != null && s1 != s2

    fun subString(s: String, from: Int, to: Int): String {
        val f = if (from < 0) 0 else if (from > s.length) s.length else from
        val t = if (to < 0) 0 else if (to > s.length) s.length else to
        if (f <= t) {
            return s.substring(f, t)
        }
        return ""
    }

    fun subStringAfter(s: String, after: Int): String {
        return subString(s, after, s.length)
    }

    fun subStringBefore(s: String, before: Int): String {
        return subString(s, 0, before)
    }

    fun subString(s: String, from: String, to: String): String {
        val fromIndex = s.indexOf(from)
        if (fromIndex < 0) {
            return ""
        }
        val toIndex = s.indexOf(to)
        if (toIndex < 0) {
            return ""
        }
        return subString(s, fromIndex, toIndex)
    }

    fun subStringAfter(s: String, after: String, offset: Int = 0): String {
        val index = s.indexOf(after)
        if (index < 0) {
            return ""
        }
        return subStringAfter(s, index + offset)
    }

    fun subStringBefore(s: String, before: String, offset: Int = 0): String {
        val index = s.indexOf(before)
        if (index < 0) {
            return ""
        }
        return subStringBefore(s, index + offset)
    }

    fun subString(s: String, from: Char, to: Char): String {
        val fromIndex = s.indexOf(from)
        if (fromIndex < 0) {
            return ""
        }
        val toIndex = s.indexOf(to)
        if (toIndex < 0) {
            return ""
        }
        return subString(s, fromIndex, toIndex)
    }

    fun subStringAfter(s: String, after: Char, offset: Int = 0): String {
        val index = s.indexOf(after)
        if (index < 0) {
            return ""
        }
        return subStringAfter(s, index + offset)
    }

    fun subStringBefore(s: String, before: Char, offset: Int = 0): String {
        val index = s.indexOf(before)
        if (index < 0) {
            return ""
        }
        return subStringBefore(s, index + offset)
    }

    fun toMap(s: String, separator: String = "="): Map<String, String> {
        val list = s.split(separator)
        val size = list.size
        val map = HashMap<String, String>(1)
        for (i in list.indices step 2) {
            val key = list[i]
            val value = if (i + 1 < size) list[i + 1] else ""
            map[key] = value
        }
        return map
    }
}