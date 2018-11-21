package com.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;

import java.util.List;

/**
 * ActivityUtis的增强扩展类
 *
 * @author lzy
 * create at 2018/7/25 15:46
 **/
public class ActivityUtilsExpand {

    public static void startActivityFinishOther(@NonNull final Activity activity, @NonNull final Class<? extends Activity> clz) {
        startActivityFinishOther(activity, clz, false);
    }

    /**
     * 跳转到指定的页面并关闭其它的页面
     *
     * @author lzy
     * create at 2018/7/25 16:14
     **/
    public static void startActivityFinishOther(@NonNull final Activity activity, @NonNull final Class<? extends Activity> clz, boolean isOpenNew) {
        startActivityFinishOther(null, activity, clz, isOpenNew, -1, -1);
    }

    public static void startActivityFinishOther(@NonNull final Activity activity, @NonNull final Class<? extends Activity> clz, boolean isOpenNew,
                                                @AnimRes final int enterAnim,
                                                @AnimRes final int exitAnim) {
        startActivityFinishOther(null, activity, clz, isOpenNew, enterAnim, exitAnim);
    }

    public static void startActivityFinishOther(Bundle bundle, @NonNull final Activity activity, @NonNull final Class<? extends Activity> clz, boolean isOpenNew,
                                                @AnimRes final int enterAnim,
                                                @AnimRes final int exitAnim) {
        if (!ActivityUtils.isActivityExistsInStack(clz) || isOpenNew) {

            if (enterAnim != -1 || exitAnim != -1) {
                if (bundle == null) {
                    ActivityUtils.startActivity(activity, clz, enterAnim, exitAnim);
                } else {
                    ActivityUtils.startActivity(bundle, activity, clz, enterAnim, exitAnim);
                }
            } else {
                if (bundle == null) {
                    ActivityUtils.startActivity(activity, clz);
                } else {
                    ActivityUtils.startActivity(bundle, activity, clz);
                }

            }
        }
        ActivityUtils.finishOtherActivities(clz, true);
    }

    /**
     * 从activity列表中获取指定activity实例
     *
     * @param clazz Activity 的类对象
     * @return
     */
    public static <T extends Activity> T getActivity(Class<T> clazz) {
        List<Activity> activityList = ActivityUtils.getActivityList();
        for (Activity activity : activityList) {
            if (activity.getClass() == clazz) {
                return (T) activity;
            }
        }
        return null;
    }

    /**
     * 返回桌面(相当于点击HOME)
     *
     * @author lzy
     * create at 2018/10/17 16:29
     **/
    public static void returnHome() {
        ActivityUtils.getTopActivity().startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
    }

}
