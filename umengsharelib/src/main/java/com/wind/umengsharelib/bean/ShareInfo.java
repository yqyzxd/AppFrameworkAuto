package com.wind.umengsharelib.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by wind on 2016/11/8.
 */

public class ShareInfo implements Serializable{

    private String title;
    private String content;
    @JSONField(name = "image_url")
    private String image_url;
    @JSONField(name = "share_url")
    private String share_url;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIconUrl(String iconUrl) {
        this.image_url = iconUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.share_url = shareUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getIconUrl() {
        return image_url;
    }

    public String getShareUrl() {
        return share_url;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
