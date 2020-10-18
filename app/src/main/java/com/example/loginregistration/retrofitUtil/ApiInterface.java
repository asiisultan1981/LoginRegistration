package com.example.loginregistration.retrofitUtil;

import com.example.loginregistration.Models.User;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<Object> performUserSignUp(@Field("user_name") String userName,
                                          @Field("user_email") String userEmail,
                                          @Field("user_password") String userPassword
    );


    @FormUrlEncoded
    @POST("login.php")
    Call<Object> performUserLogIn(
            @Field("user_email") String userEmail,
            @Field("user_password") String userPassword
    );



}
