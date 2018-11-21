package com.lzy.templetapp.mvp.view.main;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.common.utils.UtilsManager;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.templetapp.BuildConfig;
import com.lzy.templetapp.contant.AppConfig;
import com.lzy.templetapp.contant.HttpAgreement;
import com.lzy.templetapp.service.InitAppService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.thirdmodule.okgo.OkGoUtils;

/**
 * @author lzy
 * create at 2018/10/30 11:06
 **/
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        initHotfix();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.setIsDevelopmentDevice(this, BuildConfig.DEBUG);
        CrashReport.initCrashReport(getApplicationContext(), AppConfig.bugly_appid, true);
        UtilsManager.getInstance().init(this, new Handler(), Thread.currentThread());
        InitAppService.start(this);
        initOkGo();
    }

    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("agentKey", AppConfig.isTest ? "test" : "leo");
        headers.put("equip", "androivd");
        OkGoUtils.initOkGo(this, HttpAgreement.BASE_URL, headers);
    }

    private void initHotfix() {
        Log.i("initHotfix", "初始化开始");
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(AppConfig.sophix_idSecret, AppConfig.sophix_appSecret, AppConfig.sophix_rsaSecret)
                .setAesKey(null)
                .setEnableDebug(!AppConfig.isEableSophix)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Log.i("initSophix", "补丁加载成功");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            Log.i("initSophix", "新补丁生效需要重启应用");
                            relaunchApp();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            Log.i("initSophix", "其它错误信息：" + info + "(" + code + ")");
                        }

                    }
                }).initialize();
    }

    public void relaunchApp() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(getApplicationContext(), "新补丁生效需要重启应用", Toast.LENGTH_SHORT).show());
        handler.postDelayed(() -> {
            PackageManager packageManager = getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(getPackageName());
            if (intent == null) return;
            ComponentName componentName = intent.getComponent();
            Intent mainIntent = Intent.makeRestartActivityTask(componentName);
            startActivity(mainIntent);
            SophixManager.getInstance().killProcessSafely();
            System.exit(0);
        }, 2000);
    }


    static {//使用static代码段可以防止内存泄漏

        //设置全局默认配置（优先级最低，会被其他设置覆盖）

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setHeaderHeight(50).setPrimaryColors(Color.parseColor("#00000000"), Color.parseColor("#333333"));
                return new ClassicsHeader(context).setTextSizeTitle(14).setTextSizeTime(10).setDrawableArrowSize(16).setDrawableProgressSize(16);
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setFooterHeight(50).setEnableAutoLoadMore(false);
                return new ClassicsFooter(context).setTextSizeTitle(14).setDrawableArrowSize(16).setDrawableProgressSize(16);
            }
        });
    }
}
