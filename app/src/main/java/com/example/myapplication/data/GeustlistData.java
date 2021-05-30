package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class GeustlistData {

    @SerializedName("name")
    private String name;

    @SerializedName("live_code")
    private int live_code;

    @SerializedName("house")
    private int house;

    public GeustlistData(String name, int live_code, int house) {
        this.name = name;
        this.live_code = live_code;
        this.house = house;
    }
}
