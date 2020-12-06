package com.example.ccteam;

public class CommentItem {
    String name;
    String c_content;
    String profiles;
    String title;
    String content;

    public CommentItem(){}

    public CommentItem(String title, String content,String name, String c_content, String profile) {
        this.name = name;
        this.c_content = c_content;
        this.profiles = profile;
        this.content = content;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getProfiles() {
        return profiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProfiles(String profile) {
        this.profiles = profiles;
    }

    public String getC_content() {
        return c_content;
    }

    public String getTitle() {
        return title;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
