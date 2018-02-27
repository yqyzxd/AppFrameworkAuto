package com.wind.base.response;

/**
 * Created by wind on 2017/2/27.
 */

public class PageResponse extends BaseResponse {

    private boolean isFirstPage;

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }
}
