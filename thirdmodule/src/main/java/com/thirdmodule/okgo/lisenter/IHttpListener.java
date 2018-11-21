package com.thirdmodule.okgo.lisenter;

import com.thirdmodule.okgo.exception.ApiException;

public interface IHttpListener<T> {
    void onStart();
    void onSuccess(T data);
    void onError(ApiException e);
    void onFinish();
}
