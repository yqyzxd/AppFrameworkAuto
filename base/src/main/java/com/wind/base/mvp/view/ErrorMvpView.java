package com.wind.base.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by wind on 2017/3/16.
 */

public interface ErrorMvpView extends MvpView {

    void showError(String msg);
}
