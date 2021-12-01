package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NoticeResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("rows")
    private List<Row> rows;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public List<Row> getRows() {
        return rows;
    }
    public class Row {

        public @SerializedName("title") String title;
        public @SerializedName("date") String date;
        public @SerializedName("name") String name;
        public @SerializedName("notice") String notice;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = format.format(date);
            //this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }
    }
}
