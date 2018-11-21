package com.lzy.templetapp.base.mvp;

import android.app.Activity;

import com.common.base.mvp.IBaseStatusView;
import com.lzy.templetapp.base.BaseActivity;

/**
 * 基础的基于MVP的AppCompatActivity类
 *
 * @author lzy
 * create at 2018/10/30 16:46
 **/

public abstract class BaseMvpActivity<P extends RxPresenter> extends BaseActivity implements IBaseStatusView {

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
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();

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
    public void showProgressDialog(String msg, boolean isCancle) {
        startProgressDialog(msg, isCancle);
    }

    @Override
    public void showProgressDialog(boolean isCancel) {
        startProgressDialog("", isCancel);
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
