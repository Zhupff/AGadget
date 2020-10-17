package gadget.route

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 */
internal interface GadgetRouteConstant {

    val gadgetRouteApiJarName: String get() = "gadget-route-api"

    val gadgetRoutePackage: String get() = "gadget/route/"

    val G_RouteTableName: String get() = "gadget/route/G_RouteTable"

    val G_RouterName: String get() = "gadget/route/G_Router"

    val G_RouterInitRouteTableMethodName: String get() = "initRouteTable"
}