package com.example.myapplication.data;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

public class CodeImport {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("email")
    private String email;

    @SerializedName("GaestName")
    private String GaestName;

    @SerializedName("GaestOther")
    private String GaestOther;

    @SerializedName("Date")
    private String Date;

    @SerializedName("count")
    private String count;

    public CodeImport(String email, String count, String date, EditText gaestName, EditText gaestOther) {
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }
    public String getGaestName() {
        return GaestName;
    }

    public String getGaestOther() {
        return GaestOther;
    }

    public String getDate() {
        return Date;
    }

    public String getCount() {
        return count;
    }
}