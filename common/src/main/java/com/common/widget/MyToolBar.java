package com.common.widget;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.common.R;

/**
 * @author lzy
 * create at 2018/10/30 13:59
 **/
public class MyToolBar extends Toolbar {

    private Toolbar mToolbar;
    private TextView tvTitle;

    public MyToolBar(Context context) {
        super(context);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvTitle = findViewById(R.id.tvTitle);
        mToolbar = findViewById(R.id.toolBar);
    }

    public void setCustomTitle(String text) {
        if (TextUtils.isEmpty(text)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(text);
        }
    }

    public void setToolBarBgColor(@ColorInt int colorInt) {
        mToolbar.setBackgroundColor(colorInt);
    }

}
