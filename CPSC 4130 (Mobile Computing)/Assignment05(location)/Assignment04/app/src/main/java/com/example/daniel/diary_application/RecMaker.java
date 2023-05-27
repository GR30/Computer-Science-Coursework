package com.example.daniel.diary_application;

public class RecMaker {
    private String entry_title;
    private String entry_date;
    private String enty_content;

    public RecMaker(){

    }

    public RecMaker(String title , String date, String content){
        this.entry_title=title;
        this.entry_date=date;
        this.enty_content=content;
    }

    public String getTitle() {
        return entry_title;
    }

    public void setTitle(String title) {
        this.entry_title = title;
    }

    public String getDate() {
        return entry_date;
    }

    public void setDate(String date) {
        this.entry_date = date;
    }

    public String getContent() {
        return enty_content;
    }

    public void setContent(String content) {
        this.enty_content = content;
    }


}
