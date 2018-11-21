package com.lzy.templetapp.contant;

/**
 * @author lzy
 * create at 2018/11/5 14:13
 **/
public interface AppConfig {

    /**
     * 是否是测试环境
     **/
    boolean isTest = true;
    /**
     * 是否开启log
     **/
    boolean isOpenLog = true;
    /**
     * 是否开启热修复
     **/
    boolean isEableSophix = false;

    String sophix_idSecret="";
    String sophix_appSecret="";
    String sophix_rsaSecret="";

    //友盟相关配置信息
    String channelName = "";
    String appkey = "";
    String pushSecret = "";

    //bugly
    String bugly_appid = "";
    String bugly_appkey = "";

    //蒲公英版本更新
    String pgy_appkey = ""; //测试： 正式：
}
