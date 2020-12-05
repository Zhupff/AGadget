package gadget.log;

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
public class GDLog {
    private GDLog() {}

    private static final GLogConfig G_LOG_CONFIG = GLogConfig.Companion.newDefGDLogConfig();

    public static GLogConfig config() {
        return G_LOG_CONFIG;
    }

    /** log(String tag, String str, Object... objects) **/

    public static void v(String tag, String str, Object... objects) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.VERBOSE, tag, str, objects);
    }
    public static void d(String tag, String str, Object... objects) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.DEBUG, tag, str, objects);
    }
    public static void i(String tag, String str, Object... objects) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.INFO, tag, str, objects);
    }
    public static void w(String tag, String str, Object... objects) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.WARN, tag, str, objects);
    }
    public static void e(String tag, String str, Object... objects) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.ERROR, tag, str, objects);
    }

    /** log(String tag, Object object) **/

    public static void v(String tag, Object object) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.VERBOSE, tag, object);
    }
    public static void d(String tag, Object object) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.DEBUG, tag, object);
    }
    public static void i(String tag, Object object) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.INFO, tag, object);
    }
    public static void w(String tag, Object object) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.WARN, tag, object);
    }
    public static void e(String tag, Object object) {
        GLogger.INSTANCE.l(G_LOG_CONFIG, GLogLevel.ERROR, tag, object);
    }

    /** log(String tag, Object... objects) **/

    public static void vv(String tag, Object... objects) {
        GLogger.INSTANCE.ll(G_LOG_CONFIG, GLogLevel.VERBOSE, tag, objects);
    }
    public static void dd(String tag, Object... objects) {
        GLogger.INSTANCE.ll(G_LOG_CONFIG, GLogLevel.DEBUG, tag, objects);
    }
    public static void ii(String tag, Object... objects) {
        GLogger.INSTANCE.ll(G_LOG_CONFIG, GLogLevel.INFO, tag, objects);
    }
    public static void ww(String tag, Object... objects) {
        GLogger.INSTANCE.ll(G_LOG_CONFIG, GLogLevel.WARN, tag, objects);
    }
    public static void ee(String tag, Object... objects) {
        GLogger.INSTANCE.ll(G_LOG_CONFIG, GLogLevel.ERROR, tag, objects);
    }

    /** log(String str, Object... objects) **/

    public static GLogTask V(String str, Object... objects) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.VERBOSE, str, objects);
    }
    public static GLogTask D(String str, Object... objects) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.DEBUG, str, objects);
    }
    public static GLogTask I(String str, Object... objects) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.INFO, str, objects);
    }
    public static GLogTask W(String str, Object... objects) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.WARN, str, objects);
    }
    public static GLogTask E(String str, Object... objects) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.ERROR, str, objects);
    }

    /** log(Object object) **/

    public static GLogTask V(Object object) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.VERBOSE, object);
    }
    public static GLogTask D(Object object) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.DEBUG, object);
    }
    public static GLogTask I(Object object) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.INFO, object);
    }
    public static GLogTask W(Object object) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.WARN, object);
    }
    public static GLogTask E(Object object) {
        return GLogger.INSTANCE.L(G_LOG_CONFIG, GLogLevel.ERROR, object);
    }

    /** log(Object... objects) **/

    public static GLogTask VV(Object... objects) {
        return GLogger.INSTANCE.LL(G_LOG_CONFIG, GLogLevel.VERBOSE, objects);
    }
    public static GLogTask DD(Object... objects) {
        return GLogger.INSTANCE.LL(G_LOG_CONFIG, GLogLevel.DEBUG, objects);
    }
    public static GLogTask II(Object... objects) {
        return GLogger.INSTANCE.LL(G_LOG_CONFIG, GLogLevel.INFO, objects);
    }
    public static GLogTask WW(Object... objects) {
        return GLogger.INSTANCE.LL(G_LOG_CONFIG, GLogLevel.WARN, objects);
    }
    public static GLogTask EE(Object... objects) {
        return GLogger.INSTANCE.LL(G_LOG_CONFIG, GLogLevel.ERROR, objects);
    }

    /**
     * This method will be transformed to PUBLIC during compilation.
     */
    private static GLogTask log(GLogTask task, String tag) {
        if (task != null) {
            GLogger.INSTANCE.log(task.withTag(tag));
        }
        return null;
    }
}
