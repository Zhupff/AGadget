package gadget.log

import gadget.common.util.GString

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
internal object GLogger {

    fun l(config: GLogConfig, level: GLogLevel, tag: String?, str: String?, vararg obj: Any?) {
        if (!config.needWork() || tag == null || str == null) {
            return
        }
        log(GLogTask.acquireTask()
            .withLevel(level)
            .withTag(tag)
            .withContent(GString.format(str, *obj))
            .withFormatted(config.needFormatted(level)))
    }

    fun l(config: GLogConfig, level: GLogLevel, tag: String?, obj: Any?) {
        if (!config.needWork() || tag == null || obj == null) {
            return
        }
        log(GLogTask.acquireTask()
            .withLevel(level)
            .withTag(tag)
            .withContent(obj)
            .withFormatted(config.needFormatted(level)))
    }

    fun ll(config: GLogConfig, level: GLogLevel, tag: String?, vararg obj: Any?) {
        if (!config.needWork() || tag == null) {
            return
        }
        log(GLogTask.acquireTask()
            .withLevel(level)
            .withTag(tag)
            .withContent(obj.filterNotNull())
            .withFormatted(config.needFormatted(level)))
    }

    fun L(config: GLogConfig, level: GLogLevel, str: String?, vararg obj: Any?): GLogTask? {
        if (!config.needWork() || str == null) {
            return null
        }
        return GLogTask.acquireTask()
            .withLevel(level)
            .withTag(config.getDefTag())
            .withContent(GString.format(str, *obj))
            .withFormatted(config.needFormatted(level))
    }

    fun L(config: GLogConfig, level: GLogLevel, obj: Any?): GLogTask? {
        if (!config.needWork() || obj == null) {
            return null
        }
        return GLogTask.acquireTask()
            .withLevel(level)
            .withTag(config.getDefTag())
            .withContent(obj)
            .withFormatted(config.needFormatted(level))
    }

    fun LL(config: GLogConfig, level: GLogLevel, vararg obj: Any?): GLogTask? {
        if (!config.needWork()) {
            return null
        }
        return GLogTask.acquireTask()
            .withLevel(level)
            .withTag(config.getDefTag())
            .withContent(obj.filterNotNull())
            .withFormatted(config.needFormatted(level))
    }

    /**
     * The final log method.
     */
    fun log(task: GLogTask) {
        task.run()
    }
}