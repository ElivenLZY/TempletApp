package com.common.lisenter;

import org.reactivestreams.Subscriber;

import io.reactivex.Observer;

public abstract class SubscriberImpl<T> implements Subscriber<T> {

    public String info = "";//自定义显示的提示内容

    public boolean isShowErroInfo = true; //是否显示非自定义的错误信息

    public SubscriberImpl() {
    }

    public SubscriberImpl(String info) {
        this.info = info;
    }

    public SubscriberImpl(boolean isShowErroInfo) {
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
