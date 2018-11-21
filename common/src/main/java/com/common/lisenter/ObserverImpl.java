package com.common.lisenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class ObserverImpl<T> implements Observer<T> {

    public String info = "";//自定义显示的提示内容

    public boolean isShowErroInfo = true; //是否显示非自定义的错误信息

    public ObserverImpl() {
    }

    public ObserverImpl(String info) {
        this.info = info;
    }

    public ObserverImpl(boolean isShowErroInfo) {
        this.isShowErroInfo = isShowErroInfo;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }
}
