package gadget.api.common

/**
 * @Author: Zhupf
 */
const val ESC = '\u001B'

const val CSI_RESET = "$ESC[0m"

const val CSI_VERBOSE = "$ESC[37m"
const val CSI_DEBUG = "$ESC[30m"
const val CSI_INFO = "$ESC[34m"
const val CSI_WARN = "$ESC[33m"
const val CSI_ERROR = "$ESC[31m"

fun v(msg: Any) = "${CSI_VERBOSE}${msg}${CSI_RESET}"
fun d(msg: Any) = "${CSI_DEBUG}${msg}${CSI_RESET}"
fun i(msg: Any) = "${CSI_INFO}${msg}${CSI_RESET}"
fun w(msg: Any) = "${CSI_WARN}${msg}${CSI_RESET}"
fun e(msg: Any) = "${CSI_ERROR}${msg}${CSI_RESET}"