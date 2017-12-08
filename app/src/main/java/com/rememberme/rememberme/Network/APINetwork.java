package com.rememberme.rememberme.Network;

import com.blackbutterfly.mytab.User.Results.LoginResult;
import com.blackbutterfly.mytab.User.Results.SignUpResult;
import com.blackbutterfly.mytab.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by samsung on 2017-12-08.
 */

public interface APINetwork {
// 라우터 부분
    @POST("/users/signin")
    Call<LoginResult> getLoginResult(@Body User user);

    @POST("/users/signup")
    Call<SignUpResult> getSignUpResult(@Body User user);

}
