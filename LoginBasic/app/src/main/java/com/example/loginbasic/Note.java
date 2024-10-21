package com.example.loginbasic;

import java.io.Serializable;

public class Note implements Serializable {
    int td;
    String title, createDate, content;

    public Note() {
    }

    public Note(int td, String title, String createDate, String content) {
        this.td = td;
        this.title = title;
        this.createDate = createDate;
        this.content = content;
    }

    public Note(String title, String createDate, String content) {
        this.title = title;
        this.createDate = createDate;
        this.content = content;
    }

    public int getTd() {
        return td;
    }

    public void setTd(int td) {
        this.td = td;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
