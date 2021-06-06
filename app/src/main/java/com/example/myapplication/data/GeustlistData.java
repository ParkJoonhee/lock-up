package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class GeustlistData {

    @SerializedName("id")
    private String id;



    public GeustlistData(String id) {
        this.id = id;
    }
}
