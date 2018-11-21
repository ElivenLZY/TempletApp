package com.thirdmodule.okgo.lisenter;

import com.thirdmodule.okgo.exception.ApiException;

public abstract class HttpListenerImpl<T> implements IHttpListener<T> {

    @Override
    public void onError(ApiException e) {

    }

}
