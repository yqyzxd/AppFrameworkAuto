package com.wind.appframework.login;

import com.wind.base.di.DaggerComponent;

import dagger.Subcomponent;

@Subcomponent(modules = LoginModule.class)
public interface LoginComponent extends DaggerComponent/*BaseMvpComponent<LoginView,LoginPresenter>*/{

    void inject(LoginFragment loginFragment);
}
