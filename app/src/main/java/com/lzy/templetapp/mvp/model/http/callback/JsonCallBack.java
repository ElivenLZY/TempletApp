package com.lzy.templetapp.mvp.model.http.callback;


import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.common.utils.GsonUtils;
import com.common.utils.ToastUtils;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.thirdmodule.okgo.HttpResponse;
import com.thirdmodule.okgo.exception.ApiException;
import com.thirdmodule.okgo.exception.CustomException;
import com.thirdmodule.okgo.exception.ServerException;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * @T 返回的参数类型
 * @P 传进来的参数类型
 * Created by lzy on 2017/4/6 15:48
 */

public abstract class JsonCallBack<T, P> extends AbsCallback<T> {

    public P[] params;

    public JsonCallBack() {
    }

    public JsonCallBack(P... params) {
        this.params = params;
    }


    @Override
    public T convertResponse(Response response) throws Exception {

        String data = response.body().string();
        //以下代码是通过泛型解析实际参数,泛型必须传

        //得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        Type type = params[0];

        Reader reader = new StringReader(data);
        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        JsonReader jsonReader = new JsonReader(reader);
        //有数据类型，表示有泛型对象
        T dataObj = GsonUtils.fromJson(jsonReader, type);
        response.close();
        if (dataObj == null) {
            throw new CustomException("解析错误(" + data + ")");
        } else if (dataObj instanceof HttpResponse) {
            HttpResponse baseHttpModel = (HttpResponse) dataObj;
            if (baseHttpModel.isSuccess()) {
                ToastUtils.showShort(baseHttpModel.msg);
                return dataObj;
            } else {
                int code = baseHttpModel.getCode();
                throw new ServerException(code, baseHttpModel.getMsg());
            }

        } else {//基类不是BaseHttpModel类型
            throw new CustomException("解析错误(" + data + ")");
        }

    }

    @Override
    public final void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Throwable throwable = response.getException();
        if (throwable == null) return;
        if (throwable instanceof ApiException) {
            LogUtils.e("--> e instanceof ApiException err:" + throwable + "[" + response.getRawCall().request().url() + "]");
            onError((ApiException) throwable);
        } else {
            LogUtils.e("--> e !instanceof ApiException err:" + throwable + "[" + response.getRawCall().request().url() + "]");
            onError(ApiException.handleException(throwable));
        }
    }

    public void onError(ApiException e) {
        if (e == null) return;
        String msg = e.getMessage();
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showShort(msg);
        }

    }


}
