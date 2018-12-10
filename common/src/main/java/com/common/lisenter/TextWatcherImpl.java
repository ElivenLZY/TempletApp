package com.common.lisenter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Administrator on 2018/4/20.
 */

public class TextWatcherImpl<P> implements TextWatcher {

    public P[] params;

    public TextWatcherImpl() {
    }

    public TextWatcherImpl(P... params) {
        this.params=params;
    }

    public void setParams(P... params) {
        this.params = params;
    }

    @Override
    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeTextChanged(s.toString(),start,count,after);
    }

    @Override
    public final void onTextChanged(CharSequence s, int start, int before, int count) {
        onTextChanged(s.toString(),start,before,count);
    }

    @Override
    public final void afterTextChanged(Editable s) {
        afterTextChanged(s.toString());
    }

    public void beforeTextChanged(String s, int start, int count, int after) {

    }

    public void onTextChanged(@Nullable String s, int start, int before, int count) {

    }

    public void afterTextChanged(String s) {

    }

}
