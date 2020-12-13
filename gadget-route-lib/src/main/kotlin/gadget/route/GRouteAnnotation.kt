package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class GRoutePath(val route: String) {
}


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class GRouteParser(val protocol: String)