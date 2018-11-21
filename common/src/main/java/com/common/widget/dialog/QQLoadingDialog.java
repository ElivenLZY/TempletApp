package com.common.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.R;


/**
 * Created by Administrator on 2017/3/16.
 */

public class QQLoadingDialog extends BaseDialog {

    private TextView tv_msg;

    public QQLoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_dialog_qq_loading_view;
    }

    @Override
    public void setLayoutParams(WindowManager.LayoutParams params) {
        params.gravity = Gravity.TOP;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        super.setLayoutParams(params);
    }

    @Override
    protected void initAnimation() {
    }

    @Override
    protected void initView() {
        super.initView();
        tv_msg = findViewById(R.id.tv_msg);
    }

    public void setLoadingText(CharSequence charSequence) {
        tv_msg.setText(charSequence);
    }

}
