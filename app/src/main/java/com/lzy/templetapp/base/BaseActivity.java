package com.lzy.templetapp.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.common.utils.ToastUtils;
import com.common.utils.UIUtils;
import com.common.widget.MultipleStatusView;
import com.common.widget.MyToolBar;
import com.common.widget.dialog.QQLoadingDialog;
import com.gyf.barlibrary.ImmersionBar;
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

/**
 * 基础AppCompatActivity类
 *
 * @author lzy
 * @time 2017/2/7 11:39
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    protected final String tag = getClass().getSimpleName();

    protected AppCompatActivity mActivity = null;

    protected ImmersionBar mImmersionBar = null;

    private QQLoadingDialog mQQLoadingDialog = null;

    protected MultipleStatusView mMultipleStatusView = null;

    protected SmartRefreshLayout mSmartRefreshLayout = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(layoutId());
        ButterKnife.bind(this);
        mActivity = this;
        initPresenter();
        mImmersionBar = ImmersionBar.with(this);
        initStatusBar();
        initData(savedInstanceState);
        initMultipleStatusView();
        initSmartRefreshLayout();
        initView();
        EventBus.getDefault().register(this);
        setEvent();
        loadData();

    }

    /**
     * 在setContentView()之前做的事情
     **/
    private void doBeforeSetContentView() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 获取布局文件
     *
     * @return 布局id
     */
    protected abstract int layoutId();

    /**
     * 初始化Presenter
     **/
    protected void initPresenter() {
    }

    /**
     * 初始化状态栏
     **/
    protected void initStatusBar() {
        mImmersionBar.fitsSystemWindows(true, getStatusColor())
                .statusBarColor(getStatusColor())
                .statusBarDarkFont(true)
                .keyboardEnable(true)
                .init();
//        StatusBarCompat.setStatusBarColor(mActivity,UIUtils.getColor(getStatusColor()),true);
//        BarUtils.setStatusBarVisibility();
    }

    @ColorRes
    protected int getStatusColor() {
        return R.color.white;
    }

    @ColorInt
    protected int getStatusColorInt() {
        return UIUtils.getColor(R.color.white);
    }

    /**
     * 在initView()之前初始化数据
     **/
    protected void initData(Bundle savedInstanceState) {
    }

    /**
     * 初始化多状态布局
     */
    protected void initMultipleStatusView() {
        mMultipleStatusView = findViewById(R.id.multipleStatusView);
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
        mSmartRefreshLayout = findViewById(R.id.smartRefreshLayout);
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
     */
    protected abstract void initView();

    /**
     * 初始化toolBar
     */
    protected void initToolBar(@StringRes int titleNameRes) {
        initToolBar(titleNameRes, true);
    }

    protected void initToolBar(@StringRes int titleNameRes, boolean isCanBack) {
        initToolBar(getString(titleNameRes), isCanBack);
    }

    protected void initToolBar(String titleName) {
        initToolBar(titleName, true);
    }

    protected void initToolBar(String titleName, boolean isCanBack) {
        MyToolBar toolbar = findViewById(R.id.toolBar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isCanBack);
            toolbar.setOnMenuItemClickListener(this);
            toolbar.setToolBarBgColor(UIUtils.getColor(getStatusColor()));
            toolbar.setCustomTitle(titleName);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    /**
     * 设置事件
     **/
    protected void setEvent() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Integer integer) {
    }

    /**
     * 加载数据
     **/
    protected void loadData() {
    }

    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
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
        intent.setClass(this, cls);
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
    public void finish() {
        super.finish();
        setOverridePendingTransition();
    }

    /**
     * 退出动画必须放在finish()方法里，不然没效果
     */
    protected void setOverridePendingTransition() {
        overridePendingTransition(com.common.R.anim.hold, com.common.R.anim.slide_right_exit);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (KeyboardUtils.isSoftInputVisible(mActivity)) {
//            KeyboardUtils.hideSoftInput(mActivity);
//        }
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(tag);
        stopProgressDialog();
        mImmersionBar.destroy(); //必须调用该方法，防止内存泄漏
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void showLoadingDialog(String msg, boolean isCancelable) {
        cancelLoadingDialog();
        if (mQQLoadingDialog == null) mQQLoadingDialog = new QQLoadingDialog(this);
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
