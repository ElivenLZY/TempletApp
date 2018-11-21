package com.common.utils;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

public class ScreenUtilsExpand {

    /**
     * 获取手机屏幕宽度(return dp)
     *
     * @author lzy
     * create at 2018/8/22 14:28
     **/
    public static int getScreenWidthDP() {
        return SizeUtils.px2dp(ScreenUtils.getScreenWidth());
    }

    /**
     * 获取手机屏幕高度(return dp)
     *
     * @author lzy
     * create at 2018/8/22 14:28
     **/
    public static int getScreenHeightDP() {
        return SizeUtils.px2dp(ScreenUtils.getScreenHeight());
    }
}
