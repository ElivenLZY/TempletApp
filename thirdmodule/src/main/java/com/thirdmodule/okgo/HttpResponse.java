package com.thirdmodule.okgo;

import java.io.Serializable;

/**
 * @author lzy
 * create at 2018/11/5 14:37
 **/
public class HttpResponse<T> implements Serializable {

    public int code;
    public String msg;
    public T data;

    public boolean isSuccess() {
        return code == 200 ? true : false;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}