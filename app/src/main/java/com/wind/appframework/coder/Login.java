package com.wind.appframework.coder;

import com.wind.coder.annotations.Api;
import com.wind.coder.annotations.Presenter;
import com.wind.coder.annotations.Subscriber;
import com.wind.coder.annotations.Usecase;

/**
 * Created by wind on 2018/6/3.
 */
@Api(httpMethod = Api.HttpMethod.POST,url = "/login")
@Usecase
@Subscriber
@Presenter
public class Login {
}
