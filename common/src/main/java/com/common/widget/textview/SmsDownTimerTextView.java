package com.common.widget.textview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.common.utils.CountDownTimerUtil;

/**
 * 发送短信验证码倒计时
 *
 * @author lzy
 * create at 2018/7/25 15:09
 **/
public class SmsDownTimerTextView extends AppCompatTextView {

    //总时间
    private long millisTimer = 60 * 1000;
    //间隔时间
    private long intervalTimer = 1000;

    CountDownTimerUtil countDownTimerUtil;

    public SmsDownTimerTextView(Context context) {
        this(context, null);
    }

    public SmsDownTimerTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmsDownTimerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(60 * 1000, 1000);
    }

    public void init(long millisInFuture, long countDownInterval) {
        this.millisTimer = millisInFuture;
        this.intervalTimer = countDownInterval;
        countDownTimerUtil = new CountDownTimerUtil(this, millisInFuture, countDownInterval);
    }

    public void start() {
        if (countDownTimerUtil == null)
            countDownTimerUtil = new CountDownTimerUtil(this, millisTimer, intervalTimer);
        countDownTimerUtil.start();
    }

    public void cancel() {
        if (countDownTimerUtil != null) {
            countDownTimerUtil.cancel();
            countDownTimerUtil = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        cancel();
        super.onDetachedFromWindow();
    }
}
