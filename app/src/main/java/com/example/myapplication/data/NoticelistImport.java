package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class NoticelistImport {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("ntitle")
    private String ntitle;

    @SerializedName("ndate")
    private String ndate;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getNtitle() {
        return ntitle;
    }
    public String getNdate() {
        return ndate;
    }
}
