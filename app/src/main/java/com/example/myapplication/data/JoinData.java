package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {

    @SerializedName("use")
    private int use;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("nickname")
    private String nickname;

    public JoinData(int use, String email, String password, String name, String nickname) {
        this.use = use;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }
}