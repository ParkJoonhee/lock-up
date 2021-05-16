package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {

    @SerializedName("admin")
    private int admin;

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;


    public JoinData(int admin, String id, String password, String name) {
        this.admin = admin;
        this.id = id;
        this.password = password;
        this.name = name;
    }
}