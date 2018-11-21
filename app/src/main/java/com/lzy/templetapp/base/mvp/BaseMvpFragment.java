package com.lzy.templetapp.base.mvp;

import android.app.Activity;

import com.common.base.mvp.IBaseStatusView;
import com.lzy.templetapp.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/23.
 */

public abstract class BaseMvpFragment<P extends RxPresenter> extends BaseFragment implements IBaseStatusView {

    protected P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = createPresenter();
    }

    /**
     * 创建Presenter的实例
     *
     * @return Presenter的实例
     **/
    protected abstract P createPresenter();

    @Override
    protected void initView() {

    }

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
    public Activity getIActivity() {
        return mActivity;
    }

    @Override
    public String getTagName() {
        return tag;
    }

    @Override
    public void showProgressDialog(String msg) {
        startProgressDialog(msg);
    }

    @Override
    public void showProgressDialog(String msg, boolean isCancel) {
        startProgressDialog(msg, isCancel);
    }

    @Override
    public void showProgressDialog(boolean isCancle) {
        startProgressDialog("", isCancle);
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
    public void showContentV() {
        showContent();
    }

    @Override
    public void showLoadingV() {
        showLoading();
    }

    @Override
    public void showErrorV() {
        showError();
    }

    @Override
    public void showEmptyV() {
        showEmpty();
    }

    @Override
    public void showNoNetworkV() {
        showNoNetwork();
    }

    @Override
    public void showNoLoginV() {
        showNoLogin();
    }

}
