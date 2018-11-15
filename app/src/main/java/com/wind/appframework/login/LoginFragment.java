package com.wind.appframework.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wind.appframework.R;
import com.wind.appframework.login.presenter.LoginPresenter;
import com.wind.base.mvp.view.DaggerMvpFragment;
import com.wind.base.response.BaseResponse;
import com.wind.coder.annotations.Api;
import com.wind.coder.annotations.Heros;
import com.wind.coder.annotations.Param;
import com.wind.data.login.LoginRequest;
import com.wind.data.login.LoginResponse;

import javax.inject.Inject;


@Heros(
        param = @Param(

                viewCanonicalName = "com.wind.appframework.login.LoginView",
                responseCanonicalName = "com.wind.data.login.LoginResponse",
                requestCanonicalName = "com.wind.data.login.LoginRequest"
        ),
        api = @Api(httpMethod = Api.HttpMethod.POST, url = "/login")
)
public class LoginFragment extends DaggerMvpFragment<LoginView, LoginPresenter, LoginComponent>
        implements LoginView {


    @Inject
    LoginPresenter loginPresenter;


    @Override
    protected int getLayoutRes() {

        return R.layout.activity_main;
    }

    @Override
    public LoginPresenter createPresenter() {

        return loginPresenter;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* LoginComponent.Builder mComponent=  App.get()
                .appComponent()
                .componentLoginBuilder();
        mComponent.seedInstance(this);
        LoginComponent loginComponent= (LoginComponent) mComponent.build();
        Retrofit retrofit= loginComponent.retrofit();
        System.out.println(retrofit);*/
        presenter.execute(new LoginRequest());
    }

    @Override
    public void onSuccess(LoginResponse loginResponse) {
        showError(BaseResponse.CODE_SUCCESS,"登录成功");
    }


}
