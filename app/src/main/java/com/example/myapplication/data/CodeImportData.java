package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class CodeImportData {

    @SerializedName("email")
    private String email;

    public CodeImportData(String email) {
        this.email = email;
    }
}