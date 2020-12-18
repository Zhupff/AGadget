package gadget.log

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description: Annotation for GLog&GDLog.
 */

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GLogV(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GLogD(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GLogI(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GLogW(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GLogE(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GDLogV(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GDLogD(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GDLogI(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GDLogW(val tag: String = "", vararg val option: GLogOption)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class GDLogE(val tag: String = "", vararg val option: GLogOption)