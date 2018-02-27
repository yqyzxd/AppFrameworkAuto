package com.wind.base;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.wind.base.utils.SystemUiUtil;
import com.wind.view.TitleBar;


/**
 * Created by wind on 2017/11/28.
 */

public class BaseActivity extends FragmentActivity {

    protected TitleBar mTitleBar;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        SystemUiUtil.setStatusBarColor(this, Color.WHITE);
      /*  tvTitle = findViewById(R.id.tv_title);
        View leftView = findViewById(R.id.iv_left);
        if (leftView != null) {
            leftView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
        tvRight = findViewById(R.id.tv_right);
*/
        View titleBar=$(R.id.titlebar);
        mTitleBar= (TitleBar) titleBar;
        setTitle();
    }


    public <T extends View> T $(int resId){
        return (T)super.findViewById(resId);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // MobclickAgent.onPause(this);
    }

    public boolean isFinish() {
        return this == null || isFinishing();
    }

    public Activity getActivity() {
        return this;
    }

    protected void setTitle() {
    }

}
