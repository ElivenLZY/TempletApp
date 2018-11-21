package com.common.utils;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * 限制小数的位数
 * Created by Administrator on 2018/5/11.
 */

public class DecimalDigitsInputFilter implements InputFilter {

    private int decimalDigits = 8;//小数位数

    /**
     * Constructor.
     *
     * @param decimalDigits maximum decimal digits
     */
    public DecimalDigitsInputFilter(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    //参数source：将要插入的字符串，来自键盘输入、粘贴
    //参数start：source的起始位置，为0（暂时没有发现其它值的情况）
    //参数end：source的长度
    //参数dest：EditText中已经存在的字符串
    //参数dstart：插入点的位置
    //参数dend：插入点的结束位置，一般情况下等于dstart；如果选中一段字符串（这段字符串将会被替换），dstart的值就插入点的结束位置
    @Override
    public CharSequence filter(CharSequence source,
                               int start,
                               int end,
                               Spanned dest,
                               int dstart,
                               int dend) {

//        String oldText = dest.toString();
//        if (!TextUtils.isEmpty(oldText)) {
//            if (oldText.contains(".")&&!oldText.endsWith(".")) {
//                if (oldText.contains(",")) oldText.replace(",", "");
//                if (oldText.split(".")[0].length() >= numberDigits) return "";
//            }
//        }

        int dotPos = -1;
        int len = dest.length();
        for (int i = 0; i < len; i++) {
            char c = dest.charAt(i);
            if (c == '.') {
                dotPos = i;
                break;
            }
        }
        if (dotPos >= 0) {

            // protects against many dots
            if (source.equals(".")) {
                return "";
            }
            // if the text is entered before the dot
            if (dend <= dotPos) {
                return null;
            }
            if (len - dotPos > decimalDigits) {
                return "";
            }
        }

        return null;
    }


}
