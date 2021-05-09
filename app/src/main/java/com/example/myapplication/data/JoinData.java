package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {

    @SerializedName("live_code")
    private int live_code;

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;


    public JoinData(int live_code, String id, String password, String name) {
        this.live_code = live_code;
        this.id = id;
        this.password = password;
        this.name = name;
    }
}