package com.thirdmodule.x5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


public class X5WebViewClient extends WebViewClient {

    private ProgressBar mBar;
    private boolean isAppOpen;//是否在应用内打开网页

    public X5WebViewClient(ProgressBar bar) {

        this(bar, true);
    }

    public X5WebViewClient(ProgressBar bar, boolean isAppOpen) {
        mBar = bar;
        this.isAppOpen = isAppOpen;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("X5WebViewClient", url);
        if (StringUtils.isEmpty(url)) return true;
        if (url.startsWith("http") || url.startsWith("https")) { //http和https协议开头的执行正常的流程
            view.loadUrl(url);
        } else {  //其他的URL则会开启一个Acitity然后去调用原生APP
            if (url.startsWith("sinaweibo")&&!AppUtils.isAppInstalled("com.sina.weibo")){
                ToastUtils.showShort("请先安装新浪微博~");
                return true;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            ActivityUtils.startActivity(intent);
        }
        return true;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        return super.shouldInterceptRequest(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mBar != null) mBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mBar != null) mBar.setVisibility(View.GONE);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
//        view.loadUrl("file:///android_asset/404.html");
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        handler.proceed();//接受证书
    }
}
