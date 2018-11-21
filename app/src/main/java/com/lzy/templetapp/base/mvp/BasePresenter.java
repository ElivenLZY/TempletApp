package com.lzy.templetapp.base.mvp;

import com.common.base.mvp.IBaseView;
import com.lzy.templetapp.mvp.model.db.DBHelperImpl;
import com.lzy.templetapp.mvp.model.http.HttpHelperImpl;
import com.lzy.templetapp.mvp.model.prefs.PreferencesHelperImpl;
import com.lzy.templetapp.mvp.model.sdcard.SDCardHelperImpl;

/**
 * @author lzy
 * create at 2018/10/30 16:57
 **/
public class BasePresenter<V extends IBaseView> extends RxPresenter<V> {

    public DBHelperImpl dbHelper;
    public HttpHelperImpl httpHelper;
    public PreferencesHelperImpl preferencesHelper;
    public SDCardHelperImpl sdCardHelper;

    public BasePresenter(V view) {
        super(view);
        dbHelper = new DBHelperImpl();
        httpHelper = new HttpHelperImpl();
        preferencesHelper = new PreferencesHelperImpl();
        sdCardHelper = new SDCardHelperImpl();
    }

}
