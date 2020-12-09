package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object GDoR {

    fun init(isDebug: Boolean, vararg args: TaGDoR) {
        getTaGDoRList().forEach { it.init(isDebug) }
        args.forEach { it.init(isDebug) }
    }

    /**
     * If you pick gadgetDoRCompile(), This method will be rewritten During Compilation.
     */
    private fun getTaGDoRList(): List<TaGDoR> = emptyList()
}