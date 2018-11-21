package com.lzy.templetapp.base;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.util.Log;

import com.common.utils.ToastUtils;
import com.common.widget.dialog.BaseDialog;
import com.common.widget.dialog.QQLoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;


/**
 * des:基类fragment
 * Created by lzy
 * on 2016.07.12:38
 */

public abstract class BaseDialogFragment<D extends BaseDialog> extends AppCompatDialogFragment {

    protected final String TAG = getClass().getSimpleName();

    public Activity mActivity;

    protected boolean isViewPrepared = false; // 标识fragment视图已经初始化完毕

    private boolean hasFetchData = false; // 标识已经触发过懒加载数据

    protected D mDialog;

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate===");
        mActivity = getActivity();
        initPresenter();
        EventBus.getDefault().register(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog===");
        if (mDialog == null) {
            mDialog = getDialog();
            unbinder = ButterKnife.bind(this, mDialog.getRootView());
            initView();
            setEvent();
        }
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
        return mDialog;
    }

    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (!hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }
    }

    /**
     * 获取dialog
     **/
    public abstract D getDialog();

    protected void initPresenter() {
    }

    /**
     * 初始化view
     **/
    protected abstract void initView();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Integer integer) {
    }

    /**
     * 在初始化View之后调用
     **/
    protected void setEvent() {

    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected void lazyFetchData() {
        Log.d(TAG, getClass().getName() + "------>lazyFetchData");
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        showLoadingDialog("");
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        showLoadingDialog(msg);
    }


    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg, boolean isCancel) {
        showLoadingDialog(msg, isCancel);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param isCancle
     */
    public void startProgressDialog(boolean isCancle) {
        showLoadingDialog(isCancle);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        cacleLoadindDialog();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtils.showShort(text);
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        // view被销毁后，将可以重新触发数据懒加载，因为在viewpager下，fragment不会再次新建并走onCreate的生命周期流程，将从onCreateView开始
        hasFetchData = false;
        isViewPrepared = false;
        stopProgressDialog();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void showFragment(FragmentManager manager) {
        showFragment(manager, null);
    }

    public void showFragment(FragmentManager manager, Bundle bundle) {
        if (bundle != null) setArguments(bundle);
        show(manager, TAG);
    }

    private QQLoadingDialog loadingDialog;

    private void showLoadingDialog(String msg) {
        showLoadingDialog(msg, true);
    }

    private void showLoadingDialog(boolean isCancelable) {
        showLoadingDialog("加载中，请稍后...", isCancelable);
    }

    private void showLoadingDialog(String msg, boolean isCancelable) {
        cacleLoadindDialog();
        if (loadingDialog == null) loadingDialog = new QQLoadingDialog(mActivity);
        loadingDialog.setCancelable(isCancelable);
        if (!TextUtils.isEmpty(msg)) loadingDialog.setLoadingText(msg);
        loadingDialog.show();
    }

    protected void setLoadingText(String msg) {
        if (getLoadingDialog() != null) getLoadingDialog().setLoadingText(msg);
    }

    public QQLoadingDialog getLoadingDialog() {
        return loadingDialog;
    }

    private void cacleLoadindDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

}
