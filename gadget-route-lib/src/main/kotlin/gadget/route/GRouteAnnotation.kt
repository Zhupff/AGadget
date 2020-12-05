package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class GRoutePath(val route: String)