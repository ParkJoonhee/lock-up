package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class NoticeData2 {

    @SerializedName("title")
    private String title;

    @SerializedName("notice")
    private String notice;

    @SerializedName("id")
    private String id;

    @SerializedName("admin")
    private int admin;

    public NoticeData2(String title,String notice,String id,String admin) {
        this.title = title;
        this.notice = notice;
        this.id = id;
        this.admin = Integer.parseInt(admin);
    }
}