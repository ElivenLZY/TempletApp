package com.lzy.templetapp.util;

import android.util.Log;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.StringUtils;
import com.common.utils.GsonUtils;

public class HttpHelper {

    private static final String tag="HttpHelper";

    /**
     * 加密
     *
     * @author lzy
     * create at 2018/8/6 10:16
     **/
    public static String encryptParams(final Object source) {
        if (source == null) return "";
        String data;
        if (source instanceof String) {
            data = (String) source;
        } else {
            data = GsonUtils.toJson(source);
        }
        Log.i(tag,"加密前数据===========》" + data);
        try {
//            data = RSAEncrypt.encryptByPublicKey(data, new PreferencesHelperImpl().getRsaPublicKey());
            Log.i(tag,"加密后数据===========》" + data);
        } catch (Exception e) {
            Log.e(tag,"加密失败~");
        }

        return data;
    }

    /**
     * 解密
     *
     * @author lzy
     * create at 2018/8/6 10:16
     **/
    public static String decryptParams(String source) {
        if (StringUtils.isEmpty(source)) return "";
        String data=source;
        Log.i(tag,"解密前数据===========》" + data);
        try {
//            data = new String(EncryptUtils.decryptBase64AES(source.getBytes(), InstanceManager.getAppConfigRes().secret_key.getBytes(), "AES/ECB/PKCS7Padding",null));
            Log.i(tag,"解密后数据===========》" + data);
        } catch (Exception e) {
            Log.e(tag,"解密失败~");
        }

        return data;
    }

}
