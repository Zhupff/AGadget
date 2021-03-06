package gadget.api.closure

import gadget.api.common.Logln
import gadget.api.common.i
import gadget.api.plugin.GadgetBaseTransform
import gadget.api.plugin.GadgetBaseTransform.GadgetBaseTransformer
import groovy.lang.Closure

/**
 * @Author: Zhupf
 * Description:
 */
abstract class BaseClosure {
    fun delegate(closure: Closure<Any>?) {
        closure?.let {
            it.delegate = this
            it.call()
        }
        selfCheck()
    }

    open fun selfCheck() {
        Logln.min("%s self-check!", i(javaClass.simpleName))
    }
}


/**
 * Gadget.compose {
 *     logger {
 *         minLevel = "v"
 *         maxLevel = "e"
 *     }
 * }
 */
class GadgetLoggerClosure : BaseClosure() {

    var minLevel: String? = null

    var maxLevel: String? = null

    override fun selfCheck() {
        super.selfCheck()
        minLevel = when (minLevel?.toLowerCase()) {
            "e", "error" -> "Error"
            "w", "warn" -> "Warn"
            "i", "info" -> "Info"
            "d", "debug" -> "Debug"
            "v", "verbose" -> "Verbose"
            else -> "Verbose"
        }
        maxLevel = when (maxLevel?.toLowerCase()) {
            "v", "verbose" -> "Verbose"
            "d", "debug" -> "Debug"
            "i", "info" -> "Info"
            "w", "warn" -> "Warn"
            "e", "error" -> "Error"
            else -> "Error"
        }
        Logln.min("Gadget Logger, minLevel:%s maxLevel:%s", i(minLevel!!), i(maxLevel!!))
    }
}


/**
 * Gadget.compose {
 *     transform {
 *         transformers = [
 *             new ATransformer(),
 *             new BTransformer(),
 *             ...
 *         ]
 *         transforms = [
 *             new CTransform(),
 *             new DTransform().withTransformers(
 *                 new ETransformer(),
 *                 new FTransformer(),
 *                 ...
 *             ),
 *             ...
 *         ]
 *     }
 * }
 */
class GadgetTransformClosure : BaseClosure() {

    var transformers: ArrayList<GadgetBaseTransformer>? = null

    var transforms: ArrayList<GadgetBaseTransform>? = null

    override fun selfCheck() {
        super.selfCheck()
        if (transformers.isNullOrEmpty()) {
            transformers = ArrayList(0)
        }
        if (transforms.isNullOrEmpty()) {
            transforms = ArrayList(0)
        }
    }
}