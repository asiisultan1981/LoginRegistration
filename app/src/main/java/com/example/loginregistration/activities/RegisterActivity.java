package com.example.loginregistration.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginregistration.Models.User;
import com.example.loginregistration.R;
import com.example.loginregistration.databinding.ActivityRegisterBinding;
import com.example.loginregistration.retrofitUtil.ApiClient;
import com.example.loginregistration.retrofitUtil.ApiInterface;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "register";
    private ActivityRegisterBinding registerBinding;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        registerBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        registerBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performUserSignUp();
                registerBinding.showProgress.setVisibility(View.VISIBLE);
            }
        });

    }

    private void performUserSignUp() {
        String userName = registerBinding.userName.getText().toString();
        String userPassword = registerBinding.password.getText().toString();
        String name = registerBinding.name.getText().toString();
        Log.d(TAG, "performSignUp: called");


        Call<User> call = apiInterface.performUserSignUp(userName,userPassword,name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(RegisterActivity.this,"Registered successfully with " +"\n"+
                                    response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Registration Failed, " +"\n"+
                                    "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

//        call.enqueue(new Callback<User>() {
//
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.d(TAG, "onResponse: "+response);
//                Log.e(TAG, "onResponse: "+response );
//                if (response.code() == 200){
//                    if (response.body().getStatus().equals("OK")){
//                        if (response.body().getResultCode() == 1){
//                            Toast.makeText(RegisterActivity.this,"Registration Scuccess, " +"\n"+
//                                    "Now you can Log in", Toast.LENGTH_SHORT).show();
//                            onBackPressed();
//                            finish();
//
//                        }else{
//                            displayUserInfo("User already exists...");
//                        }
//
//                    }else{
//                        displayUserInfo("Some thing went wrong...");
//                        registerBinding.password.setText("");
//                    }
//
//                }else{
//                    displayUserInfo("Some thing went wrong...");
//                    registerBinding.password.setText("");
//                    Log.d(TAG, "onResponse: "+response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
    }

    private void displayUserInfo(String message){
        Snackbar.make(registerBinding.myCoordinatorLayout,message, Snackbar.LENGTH_SHORT).show();
        registerBinding.password.setText("");
        registerBinding.showProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}