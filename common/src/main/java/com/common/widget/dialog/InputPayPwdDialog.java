package com.common.widget.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.common.R;
import com.common.widget.PasswordView;
import com.common.widget.XNumberKeyboardView;


/**
 * 输入支付密码
 *
 * @author lzy
 * create at 2018/11/1 15:53
 **/

public class InputPayPwdDialog extends BaseDialog implements View.OnClickListener {

    PasswordView passwordView;
    XNumberKeyboardView numberKeyboardView;

    public InputPayPwdDialog(@NonNull Activity context) {
        super(context);
    }

    @Override
    protected void doBeforeOnCreateView(int gravity) {
        super.doBeforeOnCreateView(Gravity.BOTTOM);
    }

    @Override
    public void setLayoutParams(WindowManager.LayoutParams params) {
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        super.setLayoutParams(params);
    }

    @Override
    protected void initAnimation() {
        setBottomAnimation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_dialog_input_pay_pwd;
    }

    @Override
    protected void initView() {
        super.initView();
        passwordView = findViewById(R.id.passwordView);
        numberKeyboardView = findViewById(R.id.numberKeyboardView);
        findViewById(R.id.iv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_forgetPwd).setOnClickListener(this);
        passwordView.bindKeyBordView(numberKeyboardView);
        passwordView.setPasswordListener(new PasswordView.PasswordListener() {
            @Override
            public void passwordChange(String changeText) {
//                ToastUtils.showShort(changeText);
            }

            @Override
            public void passwordComplete(String resultPassword) {
                cancel();
                if (mOnEventLisenter != null) mOnEventLisenter.onInputPwdComplete(resultPassword);
            }

            @Override
            public void keyEnterPress(String password, boolean isComplete) {
//                ToastUtils.showShort(password+"==>"+isComplete);
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_forgetPwd) {
            if (mOnEventLisenter != null) mOnEventLisenter.onClickForgetPwd();
        }
        cancel();
    }

    private OnEventLisenter mOnEventLisenter;

    public InputPayPwdDialog setOnEventLisenter(OnEventLisenter onEventLisenter) {
        this.mOnEventLisenter = onEventLisenter;
        return this;
    }

    public interface OnEventLisenter {
        void onInputPwdComplete(String result);

        void onClickForgetPwd();
    }


}
