package com.example.ccteam;

public class Board {
    public String title;
    public String content;

    public Board(){}

    public Board(String title, String content){

        this.title = title;
        this.content = content;
    }

    public String getTitle(){
        return this.title;
    }
    public  void setTitle(){
        this.title = title;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(){
        this.content = content;
    }
}
