package com.lzy.templetapp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.lzy.templetapp.R;
import com.thirdmodule.x5.X5WebView;

import butterknife.BindView;

/**
 * @author lzy
 * create at 2018/11/27 15:22
 **/
public class SimpleWebViewActivity extends BaseX5WebViewActivity {

    @BindView(R.id.webView)
    X5WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String title, webUrl;

    @Override
    protected int layoutId() {
        return R.layout.activity_simple_webview;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        title = getIntent().getStringExtra("title");
        webUrl = getIntent().getStringExtra("webUrl");
    }

    @Override
    protected void initView() {
        initToolBar(title);
        initWebSettings(webView, progressBar);
        webView.loadUrl(webUrl);
    }

    public static void toActivity(Context context, String title, String webUrl) {
        Intent intent = new Intent(context, SimpleWebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("webUrl", webUrl);
        context.startActivity(intent);
    }

}
