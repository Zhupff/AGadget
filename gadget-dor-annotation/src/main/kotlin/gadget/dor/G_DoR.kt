package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object G_DoR {

    /**
     * If you pick gadgetDoRPlugin(), This method will be rewritten During Compilation.
     */
    private fun _getTaG_DoR(): List<TaG_DoR> = emptyList()

    fun init(isDebug: Boolean, vararg dor: TaG_DoR) {
        _getTaG_DoR().forEach { it.init(isDebug) }
        dor.forEach { it.init(isDebug) }
    }
}