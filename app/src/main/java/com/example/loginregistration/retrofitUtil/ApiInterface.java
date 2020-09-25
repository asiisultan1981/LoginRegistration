package com.example.loginregistration.retrofitUtil;

import com.example.loginregistration.Models.User;

import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Query;

public interface ApiInterface {

    @GET("register.php")
    Call<User>performUserSignUp(@Query("user_name") String userName,
                                @Query("password") String password,
                                @Query("name") String name);



    @GET("login.php")
    Call<User>performUserLogIn(@Query("user_name") String userName,
                               @Query("password") String password);
}
