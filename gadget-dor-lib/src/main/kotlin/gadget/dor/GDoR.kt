package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
object GDoR {

    fun init(isDebug: Boolean, vararg args: GDoRTable) {
        getGDoRTables().forEach { it.init(isDebug) }
        args.forEach { it.init(isDebug) }
    }

    /**
     * If you pick gadgetDoRCompile(), This method will be rewritten During Compilation.
     */
    private fun getGDoRTables(): List<GDoRTable> = emptyList()
}