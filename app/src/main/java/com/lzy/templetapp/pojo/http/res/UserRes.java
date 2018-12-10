package com.lzy.templetapp.pojo.http.res;

import com.blankj.utilcode.util.StringUtils;

import java.io.Serializable;

/**
 * @author lzy
 * create at 2018/11/5 16:07
 **/
public class UserRes implements Serializable {

    public long user_id;                //用户ID
    public String acct;                 //用户账号
    public String name;                 //证件实名
    public String id_card;              //证件号
    public String bank_icon;            //银行图标
    public String bank_display;         //银行默认图标
    public String bank_name;            //银行名
    public String bank_card;            //银行卡号
    public String bank_user;            //银行卡实名
    public boolean has_id;               //是否已经身份认证
    public boolean has_bank;             //是否已经绑定银行卡
    public boolean has_pay_pass;         //是否设置了支付密码

    public String protocol_no;   //富友支付当前用户协议号

    public String getBankInfo() {
        StringBuilder builder = new StringBuilder(bank_name);
        if (StringUtils.isEmpty(bank_card) || bank_card.length() < 4) return builder.toString();
        builder.append("(");
        builder.append(bank_card.substring(bank_card.length() - 4, bank_card.length()));
        builder.append(")");
        return builder.toString();
    }

}
