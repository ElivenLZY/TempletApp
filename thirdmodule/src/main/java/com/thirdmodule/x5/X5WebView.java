package com.thirdmodule.x5;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.CookieManager;

import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by Administrator on 2018/1/22.
 */

public class X5WebView extends WebView {

    public X5WebView(Context context) {
        this(context, null);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }


    private void init() {
        WebSettings mSettings = getSettings();
        requestFocusFromTouch();
        setClickable(true);
        mSettings.setJavaScriptEnabled(true);
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mSettings.setPluginState(WebSettings.PluginState.ON);
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        mSettings.setSupportZoom(true);//是否可以缩放，默认true
        mSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        mSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        mSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题

        mSettings.setDisplayZoomControls(false);
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mSettings.supportMultipleWindows();
        mSettings.setSupportMultipleWindows(true);
        mSettings.setDomStorageEnabled(true);
        mSettings.setDatabaseEnabled(true);
        mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mSettings.setAppCacheEnabled(true);
        mSettings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        mSettings.setAllowFileAccess(true);
        mSettings.setAllowContentAccess(true);
        mSettings.setNeedInitialFocus(true);
        if (Build.VERSION.SDK_INT >= 19) {
            mSettings.setLoadsImagesAutomatically(true);
        } else {
            mSettings.setLoadsImagesAutomatically(false);
        }
        CookieSyncManager.createInstance(getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        CookieSyncManager.getInstance().sync();
        mSettings.setNeedInitialFocus(true);
        mSettings.setDefaultTextEncodingName("UTF-8");


    }


}
