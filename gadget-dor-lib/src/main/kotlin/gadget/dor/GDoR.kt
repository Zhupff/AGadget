package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object GDoR {

    fun init(isDebug: Boolean, vararg dor: GDoRInterface) {
        getDoRList().forEach { it.init(isDebug) }
        dor.forEach { it.init(isDebug) }
    }

    /**
     * If you pick gadgetDoRPlugin(), This method will be rewritten During Compilation.
     */
    private fun getDoRList(): List<GDoRInterface> = emptyList()
}