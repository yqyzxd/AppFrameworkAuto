package com.wind.base;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.wind.base.utils.LogUtil;
import com.wind.base.utils.Navigator;
import com.wind.base.utils.WebViewHelper;
import com.wind.umengsharelib.ShareLayout;

import butterknife.ButterKnife;


/**
 * Created by wind on 2017/3/9.
 */

public abstract class AbsH5Activity extends BaseActivity {

    protected H5Param mH5Param;
    protected WebView webView;
    protected ShareLayout mShareLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mH5Param = Navigator.getSerializableExtra(this);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        View content = findViewById(R.id.webcontent);
        if (content!=null){
            if (!mH5Param.isTitlebarOverlay()) {
                ViewGroup.LayoutParams contentLayoutParams=content.getLayoutParams();
                FrameLayout.LayoutParams lp;
                if (contentLayoutParams instanceof  FrameLayout.LayoutParams) {
                    lp = (FrameLayout.LayoutParams)contentLayoutParams;
                }else {
                    throw new IllegalArgumentException("webcontent's layoutparams must be FrameLayout.LayoutParams");
                }
                //  int size=getResources().getDimensionPixelSize(R.dimen.action_bar_height);
                TypedValue tv = new TypedValue();
                if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                    int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
                    lp.topMargin = actionBarHeight;
                }

            } else {
                mTitleBar.setLineVisibility(View.GONE);
                mTitleBar.setBackgroundColor(Color.TRANSPARENT);

            }
        }

        View view = findViewById(R.id.webView);
        webView= (WebView) view;
        mShareLayout =(ShareLayout) findViewById(R.id.share_layout);


        registerWebHandler();
        initView();
        loadH5();
    }

    /**
     * h5需要调用java时设置
     */
    protected abstract void registerWebHandler();

    public int getLayoutRes() {
        return R.layout.activity_h5;
    }

    @Override
    protected void setTitle() {
        super.setTitle();
        if (mTitleBar != null) {
            mTitleBar.setTitle(mH5Param.getTitle());
            String rightName = mH5Param.getRightBtnName();
            if (TextUtils.isEmpty(rightName)) {

                mTitleBar.setRightVisibility(View.GONE);
            } else {
                mTitleBar.setRightText(rightName);
                mTitleBar.setRightVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * 设置webview相关参数
     */
    private void initView() {
        WebViewHelper.getInstance()
                .inflateWebView(this, webView,new WebViewHelper.OnPageLoadingListener() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                LogUtil.e("onPageFinished", "url" + url);
                if (TextUtils.isEmpty(mH5Param.getTitle()) && !mH5Param.isTitlebarOverlay()) {
                    String title = webView.getTitle();
                    if (title != null && title.length() > 10) {
                    } else {
                        mTitleBar.setTitle(title);
                    }

                }

                onWebPageFinished(webView, url);
            }
        });





    }

    private void loadH5() {
        if (mH5Param.getTargetUrl().startsWith("http")) {
            webView.loadUrl(mH5Param.getTargetUrl());
        } else {
            //网页源码方式加载html
            webView.loadData(mH5Param.getTargetUrl(), "text/html; charset=UTF-8", null);//这种写法可以正确解码
        }
    }

    /**
     * h5页面加载完成回调通知
     * @param webView
     * @param url
     */
    protected void onWebPageFinished(WebView webView, String url){}



    /**
     * 刷新webview
     */
    public void refresh() {
        if (webView != null) {
            webView.clearCache(true);
            webView.reload();
        }
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

}
