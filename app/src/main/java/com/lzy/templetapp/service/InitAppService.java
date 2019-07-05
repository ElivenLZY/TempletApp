package com.lzy.templetapp.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.JobIntentService;

import com.blankj.utilcode.util.LogUtils;
import com.common.utils.UIUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;

import io.reactivex.annotations.Nullable;

/**
 * @author lzy
 * create at 2018/10/30 16:14
 **/

public class InitAppService extends JobIntentService {

    private final String TAG = getClass().getSimpleName();

    public static void start(Context context) {
        Intent intent = new Intent(context, InitAppService.class);
        enqueueWork(context, InitAppService.class, 1, intent);
    }

    @Override
    protected void onHandleWork(@Nullable Intent intent) {
        try {
            initLeakCanary();
            initX5();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, "初始化服务发生异常");
        }
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(UIUtils.getAppContext())) {
            return;
        }
        LeakCanary.install(getApplication());
    }


    private void initX5() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                if (arg0) {
//                    ToastUtils.showShort("x5内核加载成功");
                }
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }


}
