package com.wind.appframework.login;

import com.wind.base.di.DaggerComponent;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = LoginModule.class)
public interface LoginComponent extends DaggerComponent, AndroidInjector<LoginFragment>/*BaseMvpComponent<LoginView,LoginPresenter>*/ {

    //Retrofit retrofit();
    //void inject(LoginFragment loginFragment);
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<LoginFragment> {

    }
}
