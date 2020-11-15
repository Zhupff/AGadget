package gadget.dor.processor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
interface GDoRField<T> {
    val name: String
    val debug: T
    val release: T
}


data class GDoRByteField(
    override val name: String,
    override val debug: Byte,
    override val release: Byte
) : GDoRField<Byte>


data class GDoRShortField(
    override val name: String,
    override val debug: Short,
    override val release: Short
) : GDoRField<Short>


data class GDoRIntField(
    override val name: String,
    override val debug: Int,
    override val release: Int
) : GDoRField<Int>


data class GDoRLongField(
    override val name: String,
    override val debug: Long,
    override val release: Long
) : GDoRField<Long>


data class GDoRFloatField(
    override val name: String,
    override val debug: Float,
    override val release: Float
) : GDoRField<Float>


data class GDoRDoubleField(
    override val name: String,
    override val debug: Double,
    override val release: Double
) : GDoRField<Double>


data class GDoRBooleanField(
    override val name: String,
    override val debug: Boolean,
    override val release: Boolean
) : GDoRField<Boolean>

data class GDoRCharField(
    override val name: String,
    override val debug: Char,
    override val release: Char
) : GDoRField<Char>


data class GDoRStringField(
    override val name: String,
    override val debug: String,
    override val release: String
) : GDoRField<String>