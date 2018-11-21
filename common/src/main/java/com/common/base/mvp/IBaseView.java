package com.common.base.mvp;

import android.app.Activity;

/**
 * Created by gmfbilu on 2017/2/24.
 */

public interface IBaseView {
    Activity getIActivity();
    String getTagName();
    void showProgressDialog(String msg);
    void showProgressDialog(String msg, boolean isCancle);
    void showProgressDialog(boolean isCancle);
    void updateProgressText(String msg);
    void hideProgressDialog();
    void showToastMsg(String msg);

}
