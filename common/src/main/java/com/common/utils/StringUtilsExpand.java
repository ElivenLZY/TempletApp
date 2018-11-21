package com.common.utils;

import com.blankj.utilcode.util.StringUtils;

/**
 * @author lzy
 * create at 2018/11/5 15:02
 **/
public class StringUtilsExpand {

    public static boolean isEmptyToToast(String data, String toastMsg) {
        if (StringUtils.isEmpty(data)) {
            ToastUtils.showShort(toastMsg);
            return true;
        }
        return false;
    }

    public static boolean isNonEmptyToToast(String data, String toastMsg) {
        if (StringUtils.isEmpty(data)) {
            ToastUtils.showShort(toastMsg);
            return false;
        }
        return true;
    }

    public static boolean isNonEqualsToToast(String src, String des, String toastMsg) {
        if (src.equals(des)) {
            return false;
        }
        ToastUtils.showShort(toastMsg);
        return true;
    }

    public static boolean contains(String src, String des) {
        if (StringUtils.isEmpty(src)) return false;
        return src.contains(des);
    }

    public static String splitWith(String src, String splitChar, int splitNum) {
        StringBuilder builder = new StringBuilder(src.replace(" ", ""));

        int i = builder.length() / splitNum;
        int j = builder.length() % splitNum;

        for (int x = (j == 0 ? i - 1 : i); x > 0; x--) {
            builder = builder.insert(x * splitNum, splitChar);
        }

        return builder.toString();
    }

}
