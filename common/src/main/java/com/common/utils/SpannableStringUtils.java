package com.common.utils;

import android.support.annotation.ColorInt;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.List;

public class SpannableStringUtils {

    /**
     * 设置多个下标字符的前景色
     *
     * @param src            要处理的原字符串
     * @param startIndexList 要处理的字符下标集合（eg：处理第一个字符的下标就是0，第二个字符下标就是1，以此类推）
     * @param color          要处理的字符下标的颜色
     * @author lzy
     * create at 2018/9/19 10:52
     **/
    public static CharSequence setForegroundColorSpan(CharSequence src, List<Integer> startIndexList, @ColorInt int color) {
        SpannableString spannableString = new SpannableString(src);
        if (!CollectionUtils.isEmpty(startIndexList)) {
            for (Integer startIndex : startIndexList) {
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
                spannableString.setSpan(colorSpan, startIndex, startIndex + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }
}
