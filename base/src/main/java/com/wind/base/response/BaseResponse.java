package com.wind.base.response;

/**
 * Created by wind on 16/5/19.
 */
public class BaseResponse {
    /**
     * 金币不足
     */
    public static final int CODE_NO_COIN=-10000;
    /**
     * 显示需要多少金币
     */
    public static final int CODE_COIN_DIALOG=10000;
    /**
     * 成功
     */
    public static final int CODE_SUCCESS=0;

    /**
     * 不是会员
     */
    public static final int CODE_NOT_MEMBER = -20000;

    /**
     * 资料未完善
     */
    public static final int CODE_NO_PROFILE = -30000;

    /**
     * 无畅聊次数
     */
    public static final int CODE_NO_CHATCOUNT = 20000;
    /**
     * 错误码
     */

    private int err;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 时间戳
     */
    public String timestamp;

    public int getErrCode() {
        return err;
    }


    public void setErr(int err) {
        this.err = err;
    }

    public String getErrMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
