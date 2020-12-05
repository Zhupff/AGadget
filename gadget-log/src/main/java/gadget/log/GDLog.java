package gadget.log;

import gadget.common.util.GString;

/**
 * Author: Zhupf
 * E-mail: zhupfplus@gmail.com
 * Description:
 */
public class GDLog {
    private GDLog() {}

    private static final Builder builder = new Builder();

    public static Builder init() {
        return builder;
    }

    /** log(String tag, String str, Object... objects) **/

    public static void v(String tag, String str, Object... objects) {

    }

    /** log **/

    private static GLogTask log(GLogTask task, String tag) {
        log(task.withTag(tag));
        return null;
    }

    private static void log(GLogTask task) {
    }


    /**
     * GDLog setting builder.
     */
    public static final class Builder {
        private Builder() {}

        private String defTag = "GDLog";
        private boolean vFormatted = true;
        private boolean dFormatted = true;
        private boolean iFormatted = true;
        private boolean wFormatted = true;
        private boolean eFormatted = true;

        public Builder withDefTag(String tag) {
            if (GString.INSTANCE.isNullOrEmpty(tag)) {
                throw new NullPointerException("defTag can't be neither null nor empty!");
            }
            this.defTag = tag;
            return this;
        }

        public Builder withVFormatted(boolean formatted) {
            this.vFormatted = formatted;
            return this;
        }

        public Builder withDFormatted(boolean formatted) {
            this.dFormatted = formatted;
            return this;
        }

        public Builder withIFormatted(boolean formatted) {
            this.iFormatted = formatted;
            return this;
        }

        public Builder withWFormatted(boolean formatted) {
            this.wFormatted = formatted;
            return this;
        }

        public Builder withEFormatted(boolean formatted) {
            this.eFormatted = formatted;
            return this;
        }

        public Builder withAllFormatted(boolean formatted) {
            this.vFormatted = formatted;
            this.dFormatted = formatted;
            this.iFormatted = formatted;
            this.wFormatted = formatted;
            this.eFormatted = formatted;
            return this;
        }
    }
}
