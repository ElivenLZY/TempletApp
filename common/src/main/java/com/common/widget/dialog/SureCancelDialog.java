package com.common.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.common.R;

/**
 * 通用提醒弹窗样式
 *
 * @author lzy
 * create at 2018/10/31 10:22
 **/
public class SureCancelDialog extends BaseDialog implements View.OnClickListener {

    TextView tvTitle;
    FrameLayout flContent;
    TextView tvContent;

    TextView tvCancel;
    TextView tvDivider;
    TextView tvSure;

    public SureCancelDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_dialog_sure_cancel_view;
    }

    @Override
    public void setLayoutParams(WindowManager.LayoutParams params) {
//        params.width = SizeUtils.dp2px(R.dimen.dp_270);
        super.setLayoutParams(params);
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle = findViewById(R.id.tv_title);
        flContent = findViewById(R.id.flContent);
        tvContent = findViewById(R.id.tv_content);
        tvCancel = findViewById(R.id.tv_cancel);
        tvDivider = findViewById(R.id.tv_divider);
        tvSure = findViewById(R.id.tv_sure);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_sure).setOnClickListener(this);
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

    }

    public TextView getTitleView() {
        return tvTitle;
    }

    public void setContentText(String content) {
        this.tvContent.setText(content);
    }

    public TextView getContentTextView() {
        return tvContent;
    }

    public void setFlContentView(View view) {
        tvContent.setVisibility(View.GONE);
        flContent.addView(view);
    }

    public FrameLayout getFlContent(){
        return flContent;
    }

    public void setSure(String strSure) {
        if (StringUtils.isEmpty(strSure)) {
            tvSure.setVisibility(View.GONE);
            tvDivider.setVisibility(View.GONE);
        } else {
            tvSure.setVisibility(View.VISIBLE);
            tvDivider.setVisibility(View.VISIBLE);
            this.tvSure.setText(strSure);
        }

    }

    public TextView getSureView() {
        return tvSure;
    }

    public void setCancel(String strCancel) {
        if (StringUtils.isEmpty(strCancel)) {
            tvCancel.setVisibility(View.GONE);
            tvDivider.setVisibility(View.GONE);
        } else {
            tvCancel.setVisibility(View.VISIBLE);
            tvDivider.setVisibility(View.VISIBLE);
            this.tvCancel.setText(strCancel);
        }

    }

    public TextView getCancelView() {
        return tvCancel;
    }

    public void setSureListener(View.OnClickListener sureListener) {
        tvSure.setOnClickListener(sureListener);
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        tvCancel.setOnClickListener(cancelListener);
    }

    @Override
    public void onClick(View v) {
        cancel();
    }

}
