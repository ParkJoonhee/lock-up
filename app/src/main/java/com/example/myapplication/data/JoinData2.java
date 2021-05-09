package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JoinData2 {

    @SerializedName("id")
    private String id;

    public JoinData2(String id) {
        this.id = id;
    }
}