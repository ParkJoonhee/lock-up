package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class GeustlistResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("r_number")
    private String r_number;

    @SerializedName("GeustName")
    private String GeustName;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getR_number() {
        return r_number;
    }
    public String getGeustName() {
        return GeustName;
    }
}
