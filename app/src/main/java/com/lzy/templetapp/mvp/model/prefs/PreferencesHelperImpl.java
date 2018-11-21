package com.lzy.templetapp.mvp.model.prefs;

import com.blankj.utilcode.util.SPUtils;

/**
 * @author lzy
 * create at 2018/10/30 16:12
 **/

public class PreferencesHelperImpl implements IPreferencesHelper {

    private final SPUtils mSPrefs;

    public PreferencesHelperImpl() {
        mSPrefs = SPUtils.getInstance();
    }


}
