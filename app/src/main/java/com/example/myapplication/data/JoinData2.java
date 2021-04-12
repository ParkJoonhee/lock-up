package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JoinData2 {

    @SerializedName("email")
    private String email;

    public JoinData2(String email) {
        this.email = email;
    }
}