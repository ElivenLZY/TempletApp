package com.common.widget.appcompat;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.common.R;
import com.common.utils.UIUtils;

/**
 * @author lzy
 * create at 2018/10/30 20:00
 **/
public class CommonButton extends AppCompatButton {

    public CommonButton(Context context) {
        super(context);
    }

    public CommonButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAbleClick() {
        setEnabled(true);
        setBackgroundColor(UIUtils.getColor(R.color.colorAccent));
        setTextColor(UIUtils.getColor(R.color.white));
    }

    public void setDisableClick() {
        setEnabled(false);
        setBackgroundColor(UIUtils.getColor(R.color.common_btn_disable_bg));
        setTextColor(UIUtils.getColor(R.color.white));
    }

}
