package com.wind.appframework;

import android.app.Application;

import com.wind.appframework.base.di.AppComponent;
import com.wind.appframework.base.di.AppModule;
import com.wind.appframework.base.di.DaggerAppComponent;

public class App extends Application {

    private static App sInstance;
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mAppComponent = createComponent();

    }


    public static App get() {
        return sInstance;
    }
    private AppComponent createComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return mAppComponent;
    }



}
