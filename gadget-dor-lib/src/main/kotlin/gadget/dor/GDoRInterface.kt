package gadget.dor

import gadget.common.g.TaG

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
interface TaGDoR : TaG {

    override fun tag() = "TaGDoR"

    fun init(isDebug: Boolean)
}