package gadget.dor

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRByte(val debug: Byte, val release: Byte)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRShort(val debug: Short, val release: Short)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRInt(val debug: Int, val release: Int)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRLong(val debug: Long, val release: Long)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRFloat(val debug: Float, val release: Float)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRDouble(val debug: Double, val release: Double)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRBoolean(val debug: Boolean, val release: Boolean)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRChar(val debug: Char, val release: Char)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class GDoRString(val debug: String, val release: String)