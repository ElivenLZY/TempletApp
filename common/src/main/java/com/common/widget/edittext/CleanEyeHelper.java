package com.common.widget.edittext;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.common.R;

/**
 * Created by Administrator on 2017/12/19.
 */

public class CleanEyeHelper implements TextWatcher, View.OnClickListener {

    private EditText editText;
    private ImageView cleanView;
    private ImageView showView;

    /**
     * 有清空文本内容按钮
     **/
    public static void bind(EditText editText, ImageView cleanView) {
        new CleanEyeHelper(editText, cleanView);
    }

    /**
     * 有清空文本内容和显示/隐藏 密码按钮的
     **/
    public static void bind(EditText editText, ImageView cleanView, ImageView eyeView) {
        new CleanEyeHelper(editText, cleanView, eyeView);
    }

    public CleanEyeHelper(EditText editText, ImageView cleanView) {
        this(editText, cleanView, null);
    }

    public CleanEyeHelper(EditText editText, ImageView cleanView, ImageView showView) {
        this.editText = editText;
        this.cleanView = cleanView;
        this.showView = showView;
        initEvent();
    }

    private void initEvent() {
        editText.addTextChangedListener(this);
        if (cleanView != null) {
            this.cleanView.setOnClickListener(this);
        }
        if (showView != null) {
            this.showView.setOnClickListener(this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s) && cleanView.getVisibility() == View.GONE) {
            cleanView.setVisibility(View.VISIBLE);
        } else if (TextUtils.isEmpty(s)) {
            cleanView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == cleanView) {
            editText.setText("");
        } else if (view == showView) {
            if (editText.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                showView.setImageResource(R.drawable.ic_regist_eye_show);
            } else {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showView.setImageResource(R.drawable.ic_regist_eye_hide);
            }
            String pwd = editText.getText().toString();
            if (!TextUtils.isEmpty(pwd))
                editText.setSelection(pwd.length());
        }

    }

}
