package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("id")
    String id;

    @SerializedName("password")
    String password;

    public LoginData(String email, String password) {
        this.id = email;
        this.password = password;
    }
}