package com.thirdmodule.okgo.lisenter;

import com.thirdmodule.okgo.exception.ApiException;
/**
 *
 * @author lzy
 * create at 2018/11/28 11:13
 **/
public abstract class HttpListenerImpl<T> implements IHttpListener<T> {

    @Override
    public void onStart() {

    }

    @Override
    public void onError(ApiException e) {

    }

    @Override
    public void onFinish() {

    }
}
