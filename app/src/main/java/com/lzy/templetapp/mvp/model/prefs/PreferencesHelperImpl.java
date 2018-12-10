package com.lzy.templetapp.mvp.model.prefs;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.templetapp.contant.SpKeyContant;

/**
 * @author lzy
 * create at 2018/10/30 16:12
 **/

public class PreferencesHelperImpl implements IPreferencesHelper {

    private final SPUtils mSPrefs;

    public PreferencesHelperImpl() {
        mSPrefs = SPUtils.getInstance();
    }

    @Override
    public void saveUserId(long account) {
        mSPrefs.put(SpKeyContant.UserId_Key, account, true);
    }

    @Override
    public long getUserId() {
        return mSPrefs.getLong(SpKeyContant.UserId_Key);
//        return 23;
    }

    @Override
    public void removeUserId() {
        mSPrefs.remove(SpKeyContant.UserId_Key, true);
    }

    @Override
    public void saveUserAccount(String account) {
        mSPrefs.put(SpKeyContant.UserAccount_Key,account,true);
    }

    @Override
    public String getUserAccount() {
        return mSPrefs.getString(SpKeyContant.UserAccount_Key);
    }

    @Override
    public void removeUserAccount() {
        mSPrefs.remove(SpKeyContant.UserAccount_Key);
    }

    @Override
    public void saveLoginHistoryAccount(String account) {
        mSPrefs.put(SpKeyContant.Login_History_Account_Key, account);
    }

    @Override
    public String getLoginHistoryAccount() {
        return mSPrefs.getString(SpKeyContant.Login_History_Account_Key);
    }

}
