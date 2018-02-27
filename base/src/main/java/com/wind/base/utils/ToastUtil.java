package com.wind.base.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.wind.base.R;
import com.wind.base.mvp.view.ErrorMvpView;

/**
 * Created by wind on 16/5/19.
 */
public class ToastUtil {
    public static void showToast(Context context,String msg){

        showToast(context,msg,LENGTH_SHORT);


    }
    public static void showToast(Context context,int stringId){
        //Toast.makeText(context,stringId,Toast.LENGTH_SHORT).show();
        showToast(context,context.getString(stringId),LENGTH_SHORT);
    }

    public static void showNetWorkError(Context context,ErrorMvpView mView) {
        mView.showError(context.getString(R.string.network_error));
    }

    public static final int LENGTH_LONG = 3500; // 3.5 seconds
    public static final int LENGTH_SHORT = 2000; // 2 seconds

    private static View mNextView;
    private static int mGravity;
    private static final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
    private static WindowManager mWM;
    private static Handler mHanlder = new Handler();

    /**
     * init
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static void init(Context context) {
        mGravity = Gravity.CENTER;
        LayoutInflater inflate = (LayoutInflater)
                context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mNextView = inflate.inflate(R.layout.cnlib_toast_dialog, null);

        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.windowAnimations = android.R.style.Animation_Toast;
        /*if (OSJudgementUtil.isMIUI()){
            mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }else {
            mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        }*/
        mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mParams.setTitle("Toast");
        mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;


        mWM = (WindowManager)  context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        // We can resolve the Gravity here by using the Locale for getting
        // the layout direction
        final Configuration config = mNextView.getContext().getResources().getConfiguration();
        final int gravity = Gravity.getAbsoluteGravity(mGravity, config.getLayoutDirection());
        mParams.gravity = gravity;
        if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
            mParams.horizontalWeight = 1.0f;
        }
        if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
            mParams.verticalWeight = 1.0f;
        }
        mParams.x = 0;
        mParams.y = 0;
//        mParams.verticalMargin = mVerticalMargin;
//        mParams.horizontalMargin = mHorizontalMargin;
        mParams.packageName = context.getPackageName();
    }

    /**
     * Show the view for the specified duration.
     * @param context
     * @param text
     * @param duration
     */
    public static void showToast(final Context context, final CharSequence text, int duration) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }

        if (mWM == null || mNextView == null) {
            init(context);
        }
        //init(context);
        mHanlder.removeCallbacks(cancelRunable);
        mHanlder.post(new Runnable() {
            @Override
            public void run() {
                try {
                    ((TextView) mNextView.findViewById(R.id.tv_content)).setText(text);
                    if (mNextView.getParent() != null)
                        mWM.removeView(mNextView);
                    mWM.addView(mNextView, mParams);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        mHanlder.postDelayed(cancelRunable, duration);
    }

    private static Runnable cancelRunable = new Runnable() {
        @Override
        public void run() {
            cancel();
        }
    };

    /**
     * cancel toast
     */
    public static void cancel() {
        if (mNextView != null && mNextView.getParent() != null)
            mWM.removeViewImmediate(mNextView);
    }
}
