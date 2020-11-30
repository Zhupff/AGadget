package gadget.log;

import gadget.common.util.GString;

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
public final class GLog {
    private GLog() {
    }

    /** log(String tag, String str, Object... objects) **/

    public static void v(String tag, String str, Object... objects) {
        l(GLogLevel.VERBOSE, tag, str, objects);
    }

    public static void d(String tag, String str, Object... objects) {
        l(GLogLevel.DEBUG, tag, str, objects);
    }

    public static void i(String tag, String str, Object... objects) {
        l(GLogLevel.INFO, tag, str, objects);
    }

    public static void w(String tag, String str, Object... objects) {
        l(GLogLevel.WARN, tag, str, objects);
    }

    public static void e(String tag, String str, Object... objects) {
        l(GLogLevel.ERROR, tag, str, objects);
    }

    private static void l(GLogLevel level, String tag, String str, Object... objects) {
        log(GLogTask.Companion.newTask()
            .withLevel(level)
            .withTag(tag)
            .withContent(GString.INSTANCE.format(str, objects)));
    }

    /** log(String tag, Object object) **/

    public static void v(String tag, Object object) {
        l(GLogLevel.VERBOSE, tag, object);
    }

    public static void d(String tag, Object object) {
        l(GLogLevel.DEBUG, tag, object);
    }

    public static void i(String tag, Object object) {
        l(GLogLevel.INFO, tag, object);
    }

    public static void w(String tag, Object object) {
        l(GLogLevel.WARN, tag, object);
    }

    public static void e(String tag, Object object) {
        l(GLogLevel.ERROR, tag, object);
    }

    private static void l(GLogLevel level, String tag, Object object) {
        log(GLogTask.Companion.newTask()
            .withLevel(level)
            .withTag(tag)
            .withContent(object));
    }

    /** log(String tag, Object... objects) **/

    public static void vv(String tag, Object... objects) {
        ll(GLogLevel.VERBOSE, tag, objects);
    }

    public static void dd(String tag, Object... objects) {
        ll(GLogLevel.DEBUG, tag, objects);
    }

    public static void ii(String tag, Object... objects) {
        ll(GLogLevel.INFO, tag, objects);
    }

    public static void ww(String tag, Object... objects) {
        ll(GLogLevel.WARN, tag, objects);
    }

    public static void ee(String tag, Object... objects) {
        ll(GLogLevel.ERROR, tag, objects);
    }

    private static void ll(GLogLevel level, String tag, Object... objects) {
        log(GLogTask.Companion.newTask()
            .withLevel(level)
            .withTag(tag)
            .withContent(objects));
    }

    /** log(String str, Object... objects) **/

    public static GLogTask V(String str, Object... objects) {
        return L(GLogLevel.VERBOSE, str, objects);
    }

    public static GLogTask D(String str, Object... objects) {
        return L(GLogLevel.DEBUG, str, objects);
    }

    public static GLogTask I(String str, Object... objects) {
        return L(GLogLevel.INFO, str, objects);
    }

    public static GLogTask W(String str, Object... objects) {
        return L(GLogLevel.WARN, str, objects);
    }

    public static GLogTask E(String str, Object... objects) {
        return L(GLogLevel.ERROR, str, objects);
    }

    private static GLogTask L(GLogLevel level, String str, Object... objects) {
        return GLogTask.Companion.newTask()
            .withLevel(level)
            .withContent(GString.INSTANCE.format(str, objects));
    }

    /** log(Object object) **/

    public static GLogTask V(Object object) {
        return L(GLogLevel.VERBOSE, object);
    }

    public static GLogTask D(Object object) {
        return L(GLogLevel.DEBUG, object);
    }

    public static GLogTask I(Object object) {
        return L(GLogLevel.INFO, object);
    }

    public static GLogTask W(Object object) {
        return L(GLogLevel.WARN, object);
    }

    public static GLogTask E(Object object) {
        return L(GLogLevel.ERROR, object);
    }

    private static GLogTask L(GLogLevel level, Object object) {
        return GLogTask.Companion.newTask()
            .withLevel(level)
            .withContent(object);
    }

    /** log(Object... objects) **/

    public static GLogTask VV(Object... objects) {
        return LL(GLogLevel.VERBOSE, objects);
    }

    public static GLogTask DD(Object... objects) {
        return LL(GLogLevel.DEBUG, objects);
    }

    public static GLogTask II(Object... objects) {
        return LL(GLogLevel.INFO, objects);
    }

    public static GLogTask WW(Object... objects) {
        return LL(GLogLevel.WARN, objects);
    }

    public static GLogTask EE(Object... objects) {
        return LL(GLogLevel.ERROR, objects);
    }

    private static GLogTask LL(GLogLevel level, Object... objects) {
        return GLogTask.Companion.newTask()
            .withLevel(level)
            .withContent(objects);
    }

    /** log **/

    private static GLogTask log(GLogTask task, String tag) {
        log(task.withTag(tag));
        return null;
    }

    private static void log(GLogTask task) {
    }
}
