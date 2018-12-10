package com.common.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.common.R;

/**
 * 基础dialog
 *
 * @author lzy
 * create at 2018/7/9 15:09
 **/
public abstract class BaseDialog extends AppCompatDialog implements DialogInterface.OnShowListener {

    protected View rootView;

    protected LayoutParams mLayoutParams;

    protected Context mContext;

    protected int gravity=Gravity.CENTER;

    public BaseDialog(Context context) {
        this(context, R.style.BaseDialog);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        onCreateView(Gravity.CENTER);
    }

    /**
     * 装载view（不能放在oncrete()方法中，会找不到view）
     *
     * @author lzy
     * create at 2018/7/9 15:04
     **/
    private void onCreateView(int gravity) {
        doBeforeOnCreateView(gravity);
        rootView = View.inflate(mContext,getLayoutId(), null);
        setContentView(rootView);
        initAnimation();
        setOnShowListener(this);
        setLayoutParams(getDefaultLayoutParams());
        initView();
        setEvent();
    }

    protected void doBeforeOnCreateView(int gravity) {
        this.gravity=gravity;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setGravity(gravity);
        setCanceledOnTouchOutside(false);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected void initView() {

    }

    protected void setEvent() {

    }

    protected void initAnimation() {
        if (gravity==Gravity.CENTER){
            setCenterAnimation();
        }else if (gravity==Gravity.BOTTOM){
            setBottomAnimation();
        }

    }

    protected void setCenterAnimation() {
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setWindowAnimations(R.style.ZoomDialogAnimation);
    }

    protected void setBottomAnimation() {
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BaseBottomSheetDialog_windowAnim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 隐藏头部导航栏状态栏
     */
    public void skipTools() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置宽度match_parent
     */
    public void setFullScreenWidth() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
    }

    public void setOnWhole() {
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
    }

    public void setLayoutParams(LayoutParams params) {
        getWindow().setAttributes(params);
    }

    public LayoutParams getDefaultLayoutParams() {
        if (mLayoutParams == null) {
            mLayoutParams = getWindow().getAttributes();
            mLayoutParams.width = LayoutParams.WRAP_CONTENT;
            mLayoutParams.height = LayoutParams.WRAP_CONTENT;
        }
        return mLayoutParams;
    }

    public View getRootView() {
        return rootView;
    }

    public int getRootViewWith() {
        return mLayoutParams.width;
    }

    @Override
    public void onShow(DialogInterface dialog) {

    }

    public void showDialog() {
        if (!isShowing()) {
            show();
        }
    }

}
