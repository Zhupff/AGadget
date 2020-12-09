package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class GRouteUrl(val protocol: String = DEF_PROTOCOL, val route: String) {
    companion object {
        const val DEF_PROTOCOL: String = "groute"
    }
}


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class GRouteParser(val protocol: String = GRouteUrl.DEF_PROTOCOL)