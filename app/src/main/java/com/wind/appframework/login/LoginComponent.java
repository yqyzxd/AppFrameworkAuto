package com.wind.appframework.login;

import com.wind.appframework.login.presenter.LoginPresenter;
import com.wind.base.di.BaseMvpComponent;

import dagger.Subcomponent;

@Subcomponent(modules = LoginModule.class)
public interface LoginComponent extends BaseMvpComponent<LoginView,LoginPresenter>{

}
