package com.lzy.templetapp.mvp.model.prefs;

/**
 * @author lzy
 * create at 2018/10/30 16:11
 **/

public interface IPreferencesHelper {
    void saveUserId(long account);
    long getUserId();
    void removeUserId();

    void saveUserAccount(String account);
    String getUserAccount();
    void removeUserAccount();

    void saveLoginHistoryAccount(String account);
    String getLoginHistoryAccount();
}
