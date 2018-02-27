package com.wind.base;

import android.os.Environment;

import java.util.Map;

/**
 * Created by wind on 2017/3/1.
 */

public class C {
    public static class Char{
        public static final String CENTER_DOT=" · ";
    }
    public static class FORMAT{
        public static final String GIF = ".gif";
    }
    public static class Version{
        public static final String API_VERSION="1.2";
        public static final String APP_VERSION = "1.1.0";
    }
    public static class Pack{
        public static String PACK_NAME_WEIBO="com.sina.weibo";
        public static String PACK_NAME_WEIXIN="com.tencent.mm";
        public static String PACK_NAME_QQ="com.tencent.mobileqq";

        public static final String PACK_NAME_MU="com.marryu";
    }
    public static class Key{
        public static final String UMENG_APPKEY="58cf4bef3eae252b79000fca";

        public static final String WEIXIN_APPKEY="wx3d2f8e6ff9127c85";
        public static final String WEIXIN_SECRET="67a02db20d5a9631eb821b749b7c26f9";

        public static final String WEIBO_APPKEY="3819362800";
        public static final String WEIBO_SECRET="2489fffdbdd75dce29f1e845ac378436";


        public static final int REQUESTCODE_VIPCENTER = 1000;


        public static final String SPLIT_CHAR = "#";
        //是否错误重试的layout
        public static final String SHOW_ERROR_RETRY_LAYOUT = "10";
    }
    public static class Value{
        public static final String APP_FOLDER= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Dinner/";
        public static final String TEMP_IMAGE_PATH=APP_FOLDER+"/ImgTmp";
        public static final int MAX_PHOTO_COUNT = 9;

        public static final int REQUEST_CODE_GALLERY=100;
        public static final int REQUEST_CODE_CAMERA=101;

        //sp filename
        public static final String SP_FILENAME = "config";
    }
    public static class PREF_KEY{
        public static final String GENDER="gender";
        public static final String UID="uid";
        public static final String IM_TOKEN="im_token";
        /**
         * 地理位置相关
         */
        public static final String LOCATION_LATITUDE="latitude";
        public static final String LOCATION_LONGITUDE="longitude";
        public static final String LOCATION_PROVINCE="province";
        public static final String LOCATION_CITY="city";

        /**
         * 引导
         */
        public static final String MAN_GUIDE_SHOWN = "man_guide_shown";
        public static final String WOMAN_GUIDE_SHOWN = "woman_guide_shown";
        public static final String WOMAN_GUIDE_INVITE = "woman_guide_invite";
        public static final String WOMAN_GUIDE_SIGNUP = "woman_guide_signup";

        public static final String MESSAGE_NOTIFYCATION_SWITCH  = "message_notifycation_switch";

        public static final String SHOW_PARTY_TIP  = "show_party_tip";
        //女士视频引导
        public static final String WOMAN_GUIDE_VIDEO = "woman_guide_video";
    }
    public static class Api{
        /**
         * 测试地址
         */
        static String DEBUG_BASE_URL="http://tuhaofanju.dev.miaotu.net/app/";

        /**
         * 线上地址
         */
        static String RELEASE_BASE_URL="https://app.tuhaofanju.com/";

        public static String getBaseUrl(){
            return RELEASE_BASE_URL;
        }
        /**
         * 图片服务器地址
         */
        public static String IMAGE_SERVER_URL="https://file.xiake99.com/upload";

        public static String getUrl(String part){
            return getBaseUrl()+part;
        }

        public static void addCommonParams(Map<String,String> paramsMap){
        }


        /**
         * 男性注册付费页面
         * @return
         */
        public static String getManPayUrl(String uid) {
            return "http://m.travelbaby.cn/Th/apply?uid="+uid;
        }

        public static String getManCenterPayUrl(String uid) {
            //return "http://h5.xiake99.com/AppTest/th_vipCenter?uid="+uid;
            return "http://h5.xiake99.com/App/th_vipCenter?uid="+uid;
        }
    }



}
