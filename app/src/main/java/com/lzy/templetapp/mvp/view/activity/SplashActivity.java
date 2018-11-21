package com.lzy.templetapp.mvp.view.activity;

import com.common.lisenter.ObserverImpl;
import com.common.utils.RxUtils;
import com.lzy.templetapp.R;
import com.lzy.templetapp.base.mvp.BaseMvpActivity;
import com.lzy.templetapp.contant.AppConfig;
import com.lzy.templetapp.mvp.contract.TestContract;
import com.lzy.templetapp.mvp.presenter.TestPresenter;
import com.lzy.templetapp.mvp.view.main.MainActivity;
import com.taobao.sophix.SophixManager;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import io.reactivex.disposables.Disposable;

/**
 * 闪屏页
 *
 * @author lzy
 * create at 2018/10/30 16:39
 **/
public class SplashActivity extends BaseMvpActivity<TestPresenter> implements TestContract.View {

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_spalash;
    }

    @Override
    protected void initView() {
        //当有启动页时，程序切换到后台再切回来，会重新打开启动页
        if (!isTaskRoot()) {
            finish();
            return;
        }
        checkSdCardPermisstion();
    }

    private void checkSdCardPermisstion() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(permissions -> {
                    toWork();
                })
                .onDenied(permissions -> {
                    finish();
                })
                .start();
    }

    private void toWork() {
        if (AppConfig.isEableSophix) {
            SophixManager.getInstance().queryAndLoadNewPatch();
        }
        RxUtils.timer(2 * 1000, new ObserverImpl() {
            @Override
            public void onSubscribe(Disposable d) {
                mPresenter.addSubscription(d);
            }

            @Override
            public void onComplete() {
                MainActivity.toActivity(mActivity);
            }
        });
    }


}
