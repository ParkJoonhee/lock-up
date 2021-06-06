package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class GeustlistResponse {

    private int live_codeStr;
    private int houseStr;
    private String GeustNameStr;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("live_code")
    private int live_code;

    @SerializedName("house")
    private int house;

    @SerializedName("GeustName")
    private String GeustName;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public int getlive_code() {
        return live_code;
    }
    public int getHouse() {
        return house;
    }
    public String getGeustName() {
        return GeustName;
    }


    public void setlive_code(int live_code) {
        live_codeStr = live_code ;
    }
    public void setHouse(int house) {
        houseStr = house ;
    }
    public void setGeustName(String GeustName) {
        GeustNameStr = GeustName ;
    }
}
