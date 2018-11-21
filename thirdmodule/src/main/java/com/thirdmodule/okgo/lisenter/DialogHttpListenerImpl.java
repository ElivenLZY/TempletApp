package com.thirdmodule.okgo.lisenter;

import android.content.Context;

import com.common.widget.dialog.QQLoadingDialog;

/**
 *
 * @author lzy
 * create at 2018/11/13 14:42
 **/
public abstract class DialogHttpListenerImpl<T> extends HttpListenerImpl<T> {

    QQLoadingDialog loadingDialog;

    public DialogHttpListenerImpl(Context context) {
        initDialog(context);
    }

    @Override
    public void onStart() {
        showDialog("加载中，请稍后...");
    }

    @Override
    public void onFinish() {
        cacleDialog();
    }

    private void initDialog(Context context) {
        loadingDialog = new QQLoadingDialog(context);
    }

    private void showDialog(String msg) {
        cacleDialog();
        loadingDialog.setLoadingText(msg);
        loadingDialog.show();
    }

    private void cacleDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
        }
    }
}
