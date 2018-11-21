package com.common.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/5/3.
 */

public final class UIUtils {

    private UIUtils() {
    }

    public static Application getApp() {
        return UtilsManager.getInstance().getApp();
    }

    /**
     * 获取全局的上下文
     */
    public static Context getAppContext() {
        return UtilsManager.getInstance().getAppContext();
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return UtilsManager.getInstance().getMainThread();
    }

    /**
     * 获取主线程Id
     */
    public static long getMainThreadId() {
        return UtilsManager.getInstance().getMainThreadId();
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return UtilsManager.getInstance().getMainThreadHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(getAppContext());
    }

    /**
     * 获得填充器
     */
    public static View inflate(@LayoutRes int resId) {
        return getLayoutInflater().inflate(resId, null);
    }

    /**
     * 获得填充器
     */
    public static View inflate(@LayoutRes int resId, ViewGroup root) {
        return getLayoutInflater().inflate(resId, root, false);
    }

    /**
     * 获取资源对象
     */
    public static Resources getResources() {
        return getAppContext().getResources();
    }

    /**
     * 获取资源对象对应的Id（如布局：“id”；图片：“drawable”）
     */
    public static int getIdByName(String idName, String idType) {
        return getResources().getIdentifier(idName, idType,
                getAppContext().getPackageName());
    }

    /**
     * 获取文字
     */
    public static String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取数字
     */
    public static int getInteger(int resId) {
        return getResources().getInteger(resId);
    }

    /**
     * 获取数字数组
     */
    public static int[] getIntArray(int resId) {
        return getResources().getIntArray(resId);
    }

    /**
     * 获取TypedArray数组可用到drawable和color的数组
     */
    public static TypedArray getTypedArray(int resId) {
        return getResources().obtainTypedArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(@NonNull Context context, @DimenRes int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(getAppContext(),resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(getAppContext(),resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(@ColorRes int resId) {
        return ContextCompat.getColorStateList(getAppContext(),resId);
    }

    /**
     * 判断当前的线程是不是在主线程
     */
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 运行到主线程
     */
    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }


}
