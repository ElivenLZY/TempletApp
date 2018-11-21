package com.common.lisenter;

import java.util.concurrent.Callable;

public abstract class CallableImpl<V, P> implements Callable<V> {

    public P[] params;

    public CallableImpl(P... params) {
        this.params = params;
    }

    public CallableImpl() {
    }
}
