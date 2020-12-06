package gadget.common.util

import gadget.common.GConstants

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object GString {

    fun format(s: String, vararg a: Any?) = String.format(s, *a)

    fun isNullOrEmpty(s: String?) = s == null || s.trim().isEmpty()

    fun isNotNullNorEmpty(s: String?) = s != null && s.trim().isNotEmpty()

    fun areNotNullAndEqual(s1: String?, s2: String?) = s1 != null && s1 == s2

    fun areNotNullAndUnequal(s1: String?, s2: String?) = s1 != null && s2 != null && s1 != s2

    /**
     * String[after, before)
     */
    fun subString(s: String, after: Int, before: Int, def: String = GConstants.DEF_STRING): String {
        if (after < 0 || after > s.length || before < 0 || before > s.length) {
            return def
        }
        return if (after <= before) s.substring(after, before) else def
    }

    fun subString(s: String, after: String, before: String, def: String = GConstants.DEF_STRING): String {
        val afterIndex = s.indexOf(after)
        if (afterIndex < 0) {
            return def
        }
        val beforeIndex = s.indexOf(before)
        if (beforeIndex < 0) {
            return def
        }
        return subString(s, afterIndex, beforeIndex, def)
    }

    fun subString(s: String, after: Char, before: Char, def: String = GConstants.DEF_STRING): String {
        val afterIndex = s.indexOf(after)
        if (afterIndex < 0) {
            return def
        }
        val beforeIndex = s.indexOf(before)
        if (beforeIndex < 0) {
            return def
        }
        return subString(s, afterIndex, beforeIndex, def)
    }

    /**
     * String[after, )
     */
    fun subStringAfter(s: String, after: Int, def: String = GConstants.DEF_STRING): String {
        return subString(s, after, s.length, def)
    }

    fun subStringAfter(s: String, after: String, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val afterIndex = s.indexOf(after)
        return if (afterIndex < 0) def else subString(s, afterIndex + offset, s.length, def)
    }

    fun subStringAfter(s: String, after: Char, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val afterIndex = s.indexOf(after)
        return if (afterIndex < 0) def else subString(s, afterIndex + offset, s.length, def)
    }

    fun subStringAfterLast(s: String, after: String, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val afterIndex = s.lastIndexOf(after)
        return if (afterIndex < 0) def else subString(s, afterIndex + offset, s.length, def)
    }

    fun subStringAfterLast(s: String, after: Char, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val afterIndex = s.lastIndexOf(after)
        return if (afterIndex < 0) def else subString(s, afterIndex + offset, s.length, def)
    }

    /**
     * String[0, before)
     */
    fun subStringBefore(s: String, before: Int, def: String = GConstants.DEF_STRING): String {
        return subString(s, 0, before, def)
    }

    fun subStringBefore(s: String, before: String, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val beforeIndex = s.indexOf(before)
        return if (beforeIndex < 0) def else subString(s, 0, beforeIndex + offset, def)
    }

    fun subStringBefore(s: String, before: Char, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val beforeIndex = s.indexOf(before)
        return if (beforeIndex < 0) def else subString(s, 0, beforeIndex + offset, def)
    }

    fun subStringBeforeLast(s: String, before: String, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val beforeIndex = s.lastIndexOf(before)
        return if (beforeIndex < 0) def else subString(s, 0, beforeIndex + offset, def)
    }

    fun subStringBeforeLast(s: String, before: Char, offset: Int = 0, def: String = GConstants.DEF_STRING): String {
        val beforeIndex = s.lastIndexOf(before)
        return if (beforeIndex < 0) def else subString(s, 0, beforeIndex + offset, def)
    }
}