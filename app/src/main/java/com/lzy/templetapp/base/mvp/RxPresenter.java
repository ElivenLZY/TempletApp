package com.lzy.templetapp.base.mvp;


import android.app.Activity;

import com.common.base.mvp.IBasePresenter;
import com.common.base.mvp.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected Reference<V> mViewRef;//View接口类型弱引用

    protected CompositeDisposable compositeDisposable;

    public RxPresenter(V view) {
        attachView(view);
    }

    public void addSubscription(Disposable disposable) {
        if (disposable == null) return;
        if (compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

//    public <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
//        addSubscription(RxBus.getDefault().toDefaultFlowable(eventType, act));
//    }

    public void removeSubscription(Disposable disposable) {
        if (disposable == null) return;
        if (compositeDisposable != null) compositeDisposable.remove(disposable);
    }

    public void unSubscription() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }

    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public V getView() {
        return mViewRef.get();
    }

    @Override
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void detachView() {
        unSubscription();
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    @Override
    public Activity getActivity() {
        return getView().getIActivity();
    }

    @Override
    public String getTagName() {
        return getView().getTagName();
    }

}
