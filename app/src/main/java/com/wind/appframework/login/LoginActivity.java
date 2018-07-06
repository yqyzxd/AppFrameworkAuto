package com.wind.appframework.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wind.appframework.App;
import com.wind.appframework.R;
import com.wind.base.BaseInjectActivity;

public class LoginActivity extends BaseInjectActivity<LoginComponent>{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        replaceFragment(new LoginFragment());
    }

    @Override
    protected void initializeInjector() {
        mComponent= App
                .get()
                .appComponent()
                .plus(new LoginModule());
    }
}
