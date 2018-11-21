package com.lzy.templetapp.base;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.common.utils.ToastUtils;
import com.common.utils.UIUtils;
import com.common.widget.MultipleStatusView;
import com.common.widget.MyToolBar;
import com.common.widget.dialog.QQLoadingDialog;
import com.lzy.okgo.OkGo;
import com.lzy.templetapp.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;


/**
 * @author lzy
 * create at 2018/11/6 15:20
 **/

public abstract class BaseFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    protected final String tag = getClass().getSimpleName();

    public AppCompatActivity mActivity;

    protected View rootView;

    protected boolean isViewPrepared = false;// 标识fragment视图已经初始化完毕

    protected boolean hasFetchData = false;// 标识已经触发过懒加载数据

    Unbinder unbinder;

    private QQLoadingDialog mQQLoadingDialog = null;

    protected MultipleStatusView mMultipleStatusView;

    protected SmartRefreshLayout mSmartRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (AppCompatActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(layoutId(), container, false);
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initPresenter();
        initPrepare();
        initMultipleStatusView();
        initSmartRefreshLayout();
        initView();
        EventBus.getDefault().register(this);
        setEvent();

        return rootView;
    }

    protected boolean autoSize() {
        return true;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.v(tag, getClass().getName() + "------>isVisibleToUser = " + isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }
    }

    /**
     * 获取布局文件
     **/
    protected abstract int layoutId();

    /**
     * 在initView之前做的准备工作
     **/
    protected void initPrepare() {
        Log.v(tag, getClass().getName() + "------>initPrepare");
    }

    /**
     * 初始化toolBar
     **/
    protected void initToolBar(String titleName) {
        initToolBar(titleName, true);
    }

    /**
     * 初始化toolBar
     **/
    protected void initToolBar(String titleName, boolean isCanBack) {
        MyToolBar toolBar = rootView.findViewById(R.id.toolBar);
        if (toolBar == null) return;
        setHasOptionsMenu(true);
        mActivity.setSupportActionBar(toolBar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(isCanBack);
        toolBar.setOnMenuItemClickListener(this);
        toolBar.setCustomTitle(titleName);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

    /**
     * 初始化多状态布局
     */
    protected void initMultipleStatusView() {
        mMultipleStatusView = rootView.findViewById(R.id.multipleStatusView);
    }

    protected void showContent() {
        if (mMultipleStatusView != null) mMultipleStatusView.showContent();
    }

    protected void showLoading() {
        if (mMultipleStatusView != null) mMultipleStatusView.showLoading();
    }

    protected void showError() {
        if (mMultipleStatusView != null) mMultipleStatusView.showError();
    }

    protected void showEmpty() {
        if (mMultipleStatusView != null) mMultipleStatusView.showEmpty();
    }

    protected void showNoNetwork() {
        if (mMultipleStatusView != null) mMultipleStatusView.showNoNetwork();
    }

    protected void showNoLogin() {
        if (mMultipleStatusView != null) mMultipleStatusView.showNoLogin();
    }

    protected void setOnRetryClickListener(View.OnClickListener onRetryClickListener) {
        if (mMultipleStatusView != null)
            mMultipleStatusView.setOnRetryClickListener(onRetryClickListener);
    }

    protected void setNoLoginClickListener(View.OnClickListener onNoLoginClickListener) {
        if (mMultipleStatusView != null)
            mMultipleStatusView.setOnGoLoginClickListener(onNoLoginClickListener);
    }

    protected int getViewStatus() {
        if (mMultipleStatusView != null) return mMultipleStatusView.getViewStatus();
        return -1;
    }

    /**
     * 初始化下拉刷新布局
     */
    protected void initSmartRefreshLayout() {
        mSmartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
    }

    protected void autoRefresh() {
        if (mSmartRefreshLayout != null) mSmartRefreshLayout.autoRefresh();
    }

    protected void finishRefresh() {
        if (mSmartRefreshLayout == null) return;
        mSmartRefreshLayout.finishRefresh();
        if (mSmartRefreshLayout.isEnableLoadMore()) {
            //恢复没有更多数据的原始状态
            mSmartRefreshLayout.setNoMoreData(false);
        }

    }

    protected void finishLoadMore() {
        if (mSmartRefreshLayout != null) mSmartRefreshLayout.finishLoadMore();
    }

    protected void finishLoadMoreWithNoMoreData() {
        if (mSmartRefreshLayout != null) mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    protected void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        if (mSmartRefreshLayout != null)
            mSmartRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    protected void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        if (mSmartRefreshLayout != null)
            mSmartRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);
    }

    protected void setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        if (mSmartRefreshLayout != null)
            mSmartRefreshLayout.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
    }


    /**
     * 初始化view
     **/
    protected abstract void initView();

    protected void initPresenter() {
    }

    /**
     * 在初始化View之后调用
     **/
    protected void setEvent() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Integer integer) {
    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected void lazyFetchData() {
        Log.v(tag, getClass().getName() + "------>lazyFetchData");
    }


    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivity(Class<?> cls, int requestCode) {
        startActivity(cls, requestCode, null);
    }

    protected void startActivity(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void startProgressDialog() {
        startProgressDialog("");
    }

    protected void startProgressDialog(String msg) {
        startProgressDialog(msg, true);
    }

    protected void startProgressDialog(String msg, boolean isCancel) {
        showLoadingDialog(msg, isCancel);
    }

    /**
     * 停止浮动加载进度条
     */
    protected void stopProgressDialog() {
        cancelLoadingDialog();
    }

    /**
     * 短暂显示Toast提示(来自String)
     */
    protected void showShortToast(String text) {
        ToastUtils.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     */
    protected void showShortToast(int resId) {
        showShortToast(UIUtils.getString(resId));
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(tag);
        EventBus.getDefault().unregister(this);
        stopProgressDialog();
        super.onDestroyView();
        // view被销毁后，将可以重新触发数据懒加载，因为在viewpager下，fragment不会再次新建并走onCreate的生命周期流程，将从onCreateView开始
        hasFetchData = false;
        isViewPrepared = false;
        unbinder.unbind();
    }

    private void showLoadingDialog(String msg, boolean isCancelable) {
        cancelLoadingDialog();
        if (mQQLoadingDialog == null) mQQLoadingDialog = new QQLoadingDialog(mActivity);
        mQQLoadingDialog.setCancelable(isCancelable);
        if (!TextUtils.isEmpty(msg)) mQQLoadingDialog.setLoadingText(msg);
        mQQLoadingDialog.showDialog();
        mQQLoadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                OkGo.getInstance().cancelTag(tag);
            }
        });
    }

    protected void setLoadingText(String msg) {
        if (mQQLoadingDialog != null) mQQLoadingDialog.setLoadingText(msg);
    }

    /**
     * 获取加载框实例
     **/
    protected QQLoadingDialog getLoadingDialog() {
        return mQQLoadingDialog;
    }

    /**
     * 关闭加载框
     **/
    private void cancelLoadingDialog() {
        if (mQQLoadingDialog != null) {
            mQQLoadingDialog.cancel();
            mQQLoadingDialog = null;
        }
    }

}
