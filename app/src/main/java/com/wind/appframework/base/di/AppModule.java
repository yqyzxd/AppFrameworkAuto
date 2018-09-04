package com.wind.appframework.base.di;

import android.support.v4.app.Fragment;

import com.wind.appframework.login.LoginComponent;
import com.wind.appframework.login.LoginFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by wind on 16/5/18.
 */
@Module(subcomponents = LoginComponent.class)
public  abstract class AppModule {
   /* private App app;
    public AppModule(App app){
      this.app=app;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.app;
    }*/
   @Binds
   @IntoMap
   @dagger.android.support.FragmentKey(LoginFragment.class)
   abstract AndroidInjector.Factory<? extends Fragment>
   buildLoginFragmentInjectorFactory(LoginComponent.Builder builder);


}
