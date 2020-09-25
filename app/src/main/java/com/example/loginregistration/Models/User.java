package com.example.loginregistration.Models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String Id;

    @SerializedName("response")
    private String Response;

    public String getId() {
        return Id;
    }

    public String getResponse() {
        return Response;
    }
}
