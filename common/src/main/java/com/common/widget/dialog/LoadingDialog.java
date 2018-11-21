package com.common.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.common.R;
import com.github.ybq.android.spinkit.SpinKitView;


/**
 * Created by Administrator on 2017/3/16.
 */

public class LoadingDialog extends BaseDialog {

    private SpinKitView mLoadingView;
    private TextView mTextView;

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_dialog_loading_view;
    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingView = findViewById(R.id.spin_kit);
        mTextView = findViewById(R.id.name);
    }

    public void setLoadingText(CharSequence charSequence) {
        mTextView.setText(charSequence);
    }

    public void setLoadingColor(int color) {
        mLoadingView.setBackgroundColor(color);
    }

    public SpinKitView getLoadingView() {
        return mLoadingView;
    }

    public View getDialogContentView() {
        return rootView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public enum RxCancelType {normal, error, success, info}
}
