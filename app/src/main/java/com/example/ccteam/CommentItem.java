package com.example.ccteam;

public class CommentItem {
    String name;
    String profiles;
    String content;

    public CommentItem(){}

    public CommentItem(String content,String name, String profile) {
        this.name = name;
        this.profiles = profile;
        this.content = content;
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


}