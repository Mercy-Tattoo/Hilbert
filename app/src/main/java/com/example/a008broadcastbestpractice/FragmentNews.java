package com.example.a008broadcastbestpractice;

public class FragmentNews {

    private String title;
    private String content;
    private String author;
    private Boolean collect;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Boolean getCollect(){ return collect;}
    public void setCollect(Boolean collect) {this.collect = collect;}
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
