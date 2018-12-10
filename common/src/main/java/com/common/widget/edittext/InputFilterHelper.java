package com.common.widget.edittext;

import android.text.InputFilter;
import android.widget.EditText;

public class InputFilterHelper {
    /**
     * 设置最大值
     *
     * @param maxmum       允许的最大值
     * @param numOfDecimal 允许的小数位
     */
    public static void setMaxmumFilter(EditText editText,double maxmum, int numOfDecimal) {
        if (editText==null) return;
        InputFilter inputFilter=new InputFilterEditText.MaxmumFilter(0, maxmum, numOfDecimal);
        editText.setFilters(new InputFilter[]{inputFilter});
    }
}
