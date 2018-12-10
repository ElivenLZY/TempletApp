package com.lzy.templetapp.mvp.presenter;


import com.lzy.templetapp.base.mvp.BasePresenter;
import com.lzy.templetapp.mvp.contract.MainContract;

/**
 * @author lzy
 * create at 2018/10/30 16:19
 **/
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    public MainPresenter(MainContract.View view) {
        super(view);
    }


}
