package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private String id;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getId() {
        return id;
    }
}
