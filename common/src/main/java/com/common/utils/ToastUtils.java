package com.common.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;

/**
 * @author lzy
 * create at 2018/11/5 15:27
 **/
public class ToastUtils {

    public static void showShort(final String text) {
        if (StringUtils.isEmpty(text)) return;
        UIUtils.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(UIUtils.getAppContext(), text, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });

    }
}
