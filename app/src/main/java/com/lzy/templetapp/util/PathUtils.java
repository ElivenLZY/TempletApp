package com.lzy.templetapp.util;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.common.utils.BasePathUtils;
import com.lzy.templetapp.mvp.model.prefs.PreferencesHelperImpl;

import java.io.File;

/**
 * 
 * @author lzy
 * create at 2018/11/8 11:48
 **/
public class PathUtils extends BasePathUtils {

    /**
     * 获取buc钱包文件存储的文件夹
     **/
    public static File getWalletSaveFolder() {
        File filePath=new File(getPackageUserPath(),"cnst");
        FileUtils.createOrExistsDir(filePath);
        return filePath;
    }

    /**
     * 获取版本更新apk保存的路径
     **/
    public static String getUpdateApkSavePath() {
        String path = getPackagePath() + "downloadapk/" + AppUtils.getAppVersionName();
        FileUtils.createOrExistsDir(path);
        return path;
    }

    /**
     * 获取应用包名下每个用户对应的目录(用于保存每个用户相关的设置信息)
     **/
    public static String getPackageUserPath() {
        long userId = new PreferencesHelperImpl().getUserId();
        String path = getPackagePath();
        if (userId >= 0) {
            path = path + File.separator + userId + File.separator;
        }
        return path;
    }
}
