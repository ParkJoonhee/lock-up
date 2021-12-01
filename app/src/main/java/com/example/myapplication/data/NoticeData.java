package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class NoticeData {

    @SerializedName("admin")
    int admin;

    public NoticeData(String admin) {
        this.admin = Integer.parseInt(admin);
    }
}