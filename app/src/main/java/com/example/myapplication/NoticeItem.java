package com.example.myapplication;

public class NoticeItem {
    String title;
    String date;
    String name;
    String notice;


    public void setTitle(String title) {
        this.title=title;
    }

    public void setDate(String date) {
        this.date=date;
    }

    public void setName(String name) {
        this.name=name;
    }
    public void setNotice(String content){
        this.notice=notice;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getName() {
        return this.name;
    }

    public String getNotice() {
        return this.notice;
    }

    public String printContents(){
        return String.format("날짜 : %s                         작성자 : %s%n내용%n%s",date,name,notice);
    }
}
