package com.example.myapplication.data;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

public class CodeData {

    @SerializedName("id")
    private String id;

    @SerializedName("GaestName")
    private String GaestName;

    @SerializedName("GaestOther")
    private String GaestOther;

    @SerializedName("Date")
    private String Date;

    @SerializedName("count")
    private String count;

    public CodeData(String id, String count, String Date, String GaestName, String GaestOther) {
        this.id = id;
        this.count = count;
        this.Date = Date;
        this.GaestName = GaestName;
        this.GaestOther = GaestOther;
    }
}