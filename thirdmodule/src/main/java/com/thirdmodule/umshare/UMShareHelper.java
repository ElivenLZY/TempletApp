package com.thirdmodule.umshare;

import java.util.Map;

public class UMShareHelper {

    public static String getHeadUrl(Map<String, String> params) {
        if (params == null) return "";
        return params.get("iconurl");
    }

    public static String getNickName(Map<String, String> params) {
        if (params == null) return "";
        return params.get("name");
    }

    public static String getOpenId(Map<String, String> params) {
        if (params == null) return "";
        return params.get("openid");
    }

}
