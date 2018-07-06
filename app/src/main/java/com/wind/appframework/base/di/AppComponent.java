package com.wind.appframework.base.di;


import com.wind.appframework.login.LoginComponent;
import com.wind.appframework.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wind on 16/5/18.
 */
@Singleton
@Component(modules = {AppModule.class, ProviderModule.class})
public interface AppComponent {

    LoginComponent plus(LoginModule loginModule);
}
