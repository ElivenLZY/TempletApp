package com.lzy.templetapp.base;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebView;
import com.thirdmodule.okgo.OkGoUtils;
import com.thirdmodule.x5.X5WebChromeClient;
import com.thirdmodule.x5.X5WebViewClient;

import java.io.File;
import java.util.ArrayList;

/**
 * @author lzy
 * create at 2018/10/30 16:05
 **/

public abstract class BaseX5WebViewActivity extends BaseActivity {

    protected WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//防闪烁
        super.onCreate(savedInstanceState);
        hideQQBrowerSign();
    }

    public void initWebSettings(WebView webView, ProgressBar progressBar) {
        mWebView = webView;
        webView.setWebViewClient(new X5WebViewClient(progressBar));
        webView.setWebChromeClient(new X5WebChromeClient(progressBar, null));
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Log.d("onDownloadStart", "url:" + url + "\nmimetype:" + mimetype + "\ncontentDisposition:" + contentDisposition);
                startDownloadFile(url, mimetype);

            }
        });
    }

    private void startDownloadFile(String url, String mimeType) {
        if (TextUtils.isEmpty(url)) return;
        String fileName = OkGoUtils.getUrlFileName(url);//解析url中的文件名
        String fileDir = getCacheDir().getAbsolutePath() + "/webview/download";//下载文件保存的目录
        String filePath = fileDir + "/" + fileName;//下载文件保存的全路径
        if (FileUtils.isFileExists(filePath)) {//文件已存在,直接打开
            openFile(filePath);
            return;
        }
        OkGo.<File>get(url)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new FileCallback(fileDir, fileName) {
                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        ToastUtils.showShort("开始下载文件，请稍后...");
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        File file = response.body();
                        openFile(file.toString());
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        Log.d("onDownloadStart", progress.fraction + "==>" + progress.currentSize + "/" + progress.totalSize);
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        ToastUtils.showShort("文件下载失败");
                    }
                });
    }

    private void openFile(String downPath) {
        QbSdk.openFileReader(this, downPath, null, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                finish();
            }
        });
    }

    /**
     * 去除QQ浏览器推广标识
     **/
    private void hideQQBrowerSign() {
        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                ArrayList<View> outView = new ArrayList<>();
                View decorView = getWindow().getDecorView();
                decorView.findViewsWithText(outView, "QQ浏览器", View.FIND_VIEWS_WITH_TEXT);
                decorView.findViewsWithText(outView, "相关视频", View.FIND_VIEWS_WITH_TEXT);
                if (outView != null && outView.size() > 0) {
                    outView.get(0).setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onDestroy() {
        destroyWebView();
        super.onDestroy();
    }

    private void destroyWebView() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
    }

}
