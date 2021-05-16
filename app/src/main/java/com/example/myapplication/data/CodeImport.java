package com.example.myapplication.data;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

public class CodeImport {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private String id;

    @SerializedName("GuestName")
    private String GuestName;

    @SerializedName("GuestOther")
    private String GuestOther;

    @SerializedName("Date")
    private String Date;

    @SerializedName("r_number")
    private String r_number;

    public CodeImport(String id, String count, String date, EditText guestName, EditText guestOther) {
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public String getGuestName() {
        return GuestName;
    }

    public String getGuestOther() {
        return GuestOther;
    }

    public String getDate() {
        return Date;
    }

    public String getr_number() {
        return r_number;
    }
}