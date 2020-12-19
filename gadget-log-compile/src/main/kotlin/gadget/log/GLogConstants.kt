package gadget.log

/**
 * @Author: Zhupf
 * @E-mail: zhupff@qq.com
 * Description:
 */
class GLogConstants {
    companion object {
        const val GLOG_V_METHOD_NAME = "V"
        const val GLOG_D_METHOD_NAME = "D"
        const val GLOG_I_METHOD_NAME = "I"
        const val GLOG_W_METHOD_NAME = "W"
        const val GLOG_E_METHOD_NAME = "E"
        const val GLOG_VV_METHOD_NAME = "VV"
        const val GLOG_DD_METHOD_NAME = "DD"
        const val GLOG_II_METHOD_NAME = "II"
        const val GLOG_WW_METHOD_NAME = "WW"
        const val GLOG_EE_METHOD_NAME = "EE"
        val GLOG_METHODS = arrayOf(
            GLOG_V_METHOD_NAME, GLOG_D_METHOD_NAME, GLOG_I_METHOD_NAME, GLOG_W_METHOD_NAME, GLOG_E_METHOD_NAME,
            GLOG_VV_METHOD_NAME, GLOG_DD_METHOD_NAME, GLOG_II_METHOD_NAME, GLOG_WW_METHOD_NAME, GLOG_EE_METHOD_NAME
        )

        const val GLOG_V_ANNOTATION_DESC = "Lgadget/log/GLogV;"
        const val GLOG_D_ANNOTATION_DESC = "Lgadget/log/GLogD;"
        const val GLOG_I_ANNOTATION_DESC = "Lgadget/log/GLogI;"
        const val GLOG_W_ANNOTATION_DESC = "Lgadget/log/GLogW;"
        const val GLOG_E_ANNOTATION_DESC = "Lgadget/log/GLogE;"
        val GLOG_ANNOTATIONS = arrayOf(GLOG_V_ANNOTATION_DESC, GLOG_D_ANNOTATION_DESC,
            GLOG_I_ANNOTATION_DESC, GLOG_W_ANNOTATION_DESC, GLOG_E_ANNOTATION_DESC)
        const val GDLOG_V_ANNOTATION_DESC = "Lgadget/log/GDLogV;"
        const val GDLOG_D_ANNOTATION_DESC = "Lgadget/log/GDLogD;"
        const val GDLOG_I_ANNOTATION_DESC = "Lgadget/log/GDLogI;"
        const val GDLOG_W_ANNOTATION_DESC = "Lgadget/log/GDLogW;"
        const val GDLOG_E_ANNOTATION_DESC = "Lgadget/log/GDLogE;"
        val GDLOG_ANNOTATIONS = arrayOf(GDLOG_V_ANNOTATION_DESC, GDLOG_D_ANNOTATION_DESC,
            GDLOG_I_ANNOTATION_DESC, GDLOG_W_ANNOTATION_DESC, GDLOG_E_ANNOTATION_DESC)
    }
}