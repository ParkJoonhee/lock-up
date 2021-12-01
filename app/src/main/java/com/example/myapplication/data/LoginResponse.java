package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("admin")
    private int admin;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAdmin() {
        return admin;
    }
}
