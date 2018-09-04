package com.wind.appframework.base.di;


import com.wind.appframework.App;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wind on 16/5/18.
 */
@Singleton
@Component(modules = {AppModule.class, ProviderModule.class})
public interface AppComponent {
    void  inject(App app);

    //LoginComponent.Builder componentLoginBuilder();
}
