package com.lzy.templetapp.base.mvp;

import android.app.Activity;

import com.common.base.mvp.IBaseView;
import com.common.widget.dialog.BaseDialog;
import com.lzy.templetapp.base.BaseDialogFragment;

/**
 * Created by Administrator on 2018/1/23.
 */

public abstract class BaseMvpDialogFragment<P extends RxPresenter, D extends BaseDialog> extends BaseDialogFragment<D> implements IBaseView {

    protected P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    protected void setEvent() {
        super.setEvent();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }


    @Override
    public String getTagName() {
        return TAG;
    }

    @Override
    public void showProgressDialog(String msg) {
        startProgressDialog(msg);
    }

    @Override
    public void showProgressDialog(String msg, boolean isCancle) {
        startProgressDialog(msg, isCancle);
    }

    @Override
    public void showProgressDialog(boolean isCancle) {
        startProgressDialog(isCancle);
    }

    @Override
    public void updateProgressText(String msg) {
        setLoadingText(msg);
    }

    @Override
    public void hideProgressDialog() {
        stopProgressDialog();
    }

    @Override
    public void showToastMsg(String msg) {
        showShortToast(msg);
    }

    @Override
    public Activity getIActivity() {
        return mActivity;
    }

}
