package com.lzy.templetapp.util;

import com.blankj.utilcode.util.StringUtils;
import com.lzy.templetapp.mvp.model.db.liteorm.LiteDBManager;
import com.lzy.templetapp.mvp.model.prefs.PreferencesHelperImpl;
import com.lzy.templetapp.pojo.http.res.UserRes;

/**
 * @author lzy
 * create at 2018/11/5 16:41
 **/
public class ProjectUtils {

    public static boolean isLogin() {
        long userId = new PreferencesHelperImpl().getUserId();
        return userId > 0 ? true : false;
    }

    public static void saveLoginInfo(UserRes userRes, String loginAccount) {
        PreferencesHelperImpl preferencesHelper = new PreferencesHelperImpl();
        if (userRes != null) {
            preferencesHelper.saveUserId(userRes.user_id);
            preferencesHelper.saveUserAccount(userRes.acct);
        }
        if (!StringUtils.isEmpty(loginAccount)) {
            preferencesHelper.saveLoginHistoryAccount(loginAccount);
        }

    }

    public static void clearLoginInfo() {
        PreferencesHelperImpl preferencesHelper = new PreferencesHelperImpl();
        preferencesHelper.removeUserId();
        LiteDBManager.getInstance().releaseDataBase();
    }

}
