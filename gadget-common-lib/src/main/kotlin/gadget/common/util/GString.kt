package gadget.common.util

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object GString {

    fun isNullOrEmpty(s: String?) = s == null || s.trim().isEmpty()

    fun isNotNullNorEmpty(s: String?) = s != null && s.trim().isNotEmpty()

    fun areNotNullAndEqual(s1: String?, s2: String?) = s1 != null && s1 == s2

    fun areNotNullAndUnequal(s1: String?, s2: String?) = s1 != null && s2 != null && s1 != s2
}