package com.wind.appframework;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.wind.appframework.base.di.AppComponent;
import com.wind.appframework.base.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class App extends Application implements HasSupportFragmentInjector {

    private static App sInstance;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mAppComponent = createComponent();
        mAppComponent.inject(this);
    }


    public static App get() {
        return sInstance;
    }

    private AppComponent createComponent() {
        return DaggerAppComponent
                .builder()
                //.appModule(new AppModule(this))
                //.providerModule(new ProviderModule())
                .build();
    }

    public AppComponent appComponent() {
        return mAppComponent;
    }

    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector;


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
