package com.lzy.templetapp.mvp.view.main;

import android.app.Activity;

import com.blankj.utilcode.util.ActivityUtils;
import com.lzy.templetapp.R;
import com.lzy.templetapp.base.mvp.BaseMvpActivity;
import com.lzy.templetapp.mvp.contract.TestContract;
import com.lzy.templetapp.mvp.presenter.TestPresenter;

/**
 * 主页面
 *
 * @author lzy
 * create at 2018/11/21 14:36
 **/
public class MainActivity extends BaseMvpActivity<TestPresenter> implements TestContract.View {

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    public static void toActivity(Activity activity) {
        ActivityUtils.finishActivity(MainActivity.class);
        ActivityUtils.startActivity(activity, MainActivity.class, R.anim.zoom_outside_enter, R.anim.zoom_inside_exit);
        ActivityUtils.finishOtherActivities(MainActivity.class);
    }

}
