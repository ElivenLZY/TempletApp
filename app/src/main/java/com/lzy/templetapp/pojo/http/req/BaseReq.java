package com.lzy.templetapp.pojo.http.req;

import com.lzy.templetapp.mvp.model.prefs.PreferencesHelperImpl;

/**
 * @author lzy
 * create at 2018/11/5 14:48
 **/
public class BaseReq {

    public Long user_id;

    public BaseReq() {
        this.user_id = new PreferencesHelperImpl().getUserId();
    }


}
