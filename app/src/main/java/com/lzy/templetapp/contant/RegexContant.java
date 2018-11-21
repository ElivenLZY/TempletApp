package com.lzy.templetapp.contant;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface RegexContant {

    /**
     * 手机号
     **/
    String REGEX_TEL = "^(13|14|15|16|17|18|19)[0-9]{9}$";
    /**
     * 邮箱
     **/
    String REGEX_MAIL = "^\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}$";
    /**
     * 密码
     **/
    String REGEX_PWD = "^(?![0-9]*$)[a-zA-Z0-9|!@#$%^&*()_=~`+-]{6,20}$";
    /**
     * 身份证号
     **/
    String REGEX_ID_CARD = "^\\d{17}[\\d|X]|\\d{15}$";
}
