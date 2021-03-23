package com.example.bcnet_app.models;

public class Localitzacio {
    private String title;
    private String imageUrl;
    private String content;

    public Localitzacio(String imageUrl, String titol, String content) {
        this.title = titol;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public Localitzacio() {
    }
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title=title;}
    public String getImageUrl () { return imageUrl;}
    public void setImageUrl (String imageUrl) {this.imageUrl = imageUrl;}
    public String getContent () { return content;}
    public void setContent (String content) {this.content = content;}
}

