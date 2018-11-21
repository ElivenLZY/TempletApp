package com.common.base.mvp;

import android.app.Activity;
import android.content.Context;

/**
 * Created by gmfbilu on 2017/2/24.
 */

public interface IBasePresenter<V extends IBaseView>{

    /**
     * 绑定View接口
     */
    void attachView(V view);

    /**
     * 获取View接口
     */
    V getView();

    /**
     * 判断是否与View建立了关联
     */
    boolean isViewAttached();

    /**
     * 释放View接口
     */
    void detachView();

    Activity getActivity();

    String getTagName();

}
