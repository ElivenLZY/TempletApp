package com.common.utils;

import android.content.Intent;
import android.net.Uri;

public class IntentUtilsExpand {

    /**
     * 获取跳转到浏览器的意图
     *
     * @author lzy
     * create at 2018/10/17 16:56
     **/
    public static Intent getBrowesIntent(String url) {
        return getBrowesIntent(url, false);
    }

    public static Intent getBrowesIntent(String url, final boolean isNewTask) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

}
