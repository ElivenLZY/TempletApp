package com.lzy.templetapp.mvp.presenter;


import com.lzy.templetapp.base.mvp.BasePresenter;
import com.lzy.templetapp.mvp.contract.TestContract;

/**
 * @author lzy
 * create at 2018/10/30 16:19
 **/
public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {


    public TestPresenter(TestContract.View view) {
        super(view);
    }


}
