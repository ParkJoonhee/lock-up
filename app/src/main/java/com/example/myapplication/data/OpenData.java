package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class OpenData {

    @SerializedName("id")
    String id;

    @SerializedName("oc")
    String oc;

    public OpenData(String id,String oc) {
        this.id = id;
        this.oc = oc;
    }
}