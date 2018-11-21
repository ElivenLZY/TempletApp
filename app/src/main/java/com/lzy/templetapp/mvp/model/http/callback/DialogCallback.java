/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lzy.templetapp.mvp.model.http.callback;

import android.content.Context;

import com.common.widget.dialog.QQLoadingDialog;
import com.lzy.okgo.request.base.Request;

/**
 * @author lzy
 * create at 2018/10/30 16:11
 **/
public abstract class DialogCallback<T, P> extends JsonCallBack<T, P> {

    QQLoadingDialog loadingDialog;

    public DialogCallback(Context context) {
        super();
        initDialog(context);
    }

    public DialogCallback(Context context, P... params) {
        super(params);
        initDialog(context);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        showDialog("加载中，请稍后...");
    }

    @Override
    public void onFinish() {
        super.onFinish();
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
