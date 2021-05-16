package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class CodeImportData {

    @SerializedName("id")
    private String id;

    public CodeImportData(String id) {
        this.id = id;
    }
}