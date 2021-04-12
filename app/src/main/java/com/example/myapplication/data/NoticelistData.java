package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class NoticelistData {

    @SerializedName("ntitle")
    private String ntitle;

    @SerializedName("ndate")
    private String ndate;

    public NoticelistData(String ntitle, String ndate) {
        this.ntitle = ntitle;
        this.ndate = ndate;
    }

}
