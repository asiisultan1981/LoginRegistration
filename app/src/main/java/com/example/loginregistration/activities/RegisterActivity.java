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
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "register";
    private ActivityRegisterBinding registerBinding;
//    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        registerBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);

//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

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
        String userEmail = registerBinding.password.getText().toString();
        String userPassword = registerBinding.email.getText().toString();
        Log.d(TAG, "performSignUp: called");

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call call = apiInterface.performUserSignUp(userName, userEmail, userPassword);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));

                    if (!jsonObject.getBoolean("error")) {
                        Toast.makeText(RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(RegisterActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Registration Failed, " + "\n" +
                        "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


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