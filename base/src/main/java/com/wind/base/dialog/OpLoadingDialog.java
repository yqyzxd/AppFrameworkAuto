package com.wind.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.wind.base.R;


public class OpLoadingDialog extends Dialog {
    public OpLoadingDialog(Context context) {
        this(context, R.style.loading_dialog);
    }
    private TextView tv_upload_percent;
    public OpLoadingDialog(Context context, int theme) {
        super(context, theme);
       // this.setContentView(R.layout.customprogressdialog);
        this.setContentView(R.layout.wd_layout_op_loading);
        if (getContext()==null){
            return;
        }

        this.getWindow().getAttributes().gravity = Gravity.CENTER;




    }  


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {  
        if (!hasFocus) {  
            dismiss();  
        }  
    }  
}  