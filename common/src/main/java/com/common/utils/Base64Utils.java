package com.common.utils;

import android.util.Base64;

public class Base64Utils {

    public static String encode(String src) {
        try {
            return new String(Base64.encode(src.getBytes(), Base64.NO_WRAP));
        } catch (Exception e) {
            return "";
        }

    }

    public static String decode(String src) {
        try {
            return new String(Base64.decode(src, Base64.NO_WRAP));
        } catch (Exception e) {
            return "";
        }

    }
}
