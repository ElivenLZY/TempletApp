package com.common.utils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;

import java.io.File;

/**
 * @author lzy
 * create at 2018/11/8 11:51
 **/

public class BasePathUtils {

    /**
     * 获取交易二维码保存的路径
     **/
    public static File getTradQrCodeSaveFile(String fileName) {
        String path = getPackagePath() + "tradQrCode";
        File filePath = new File(path, fileName + "_" + System.currentTimeMillis() + ".jpg");
        FileUtils.createOrExistsDir(filePath);
        return filePath;
    }

    /**
     * 获取设备唯一标识码的文件存储路径
     **/
    public static String getUniversalIDPath() {
        String path = getPackagePath() + "universalId.txt";
        FileUtils.createOrExistsFile(path);
        return path;
    }

    /**
     * 获取应用包名的目录
     **/
    public static String getPackagePath() {
        String path = SDCardUtils.getSDCardPathByEnvironment() + File.separator + AppUtils.getAppPackageName() + File.separator;
        return path;
    }


}
