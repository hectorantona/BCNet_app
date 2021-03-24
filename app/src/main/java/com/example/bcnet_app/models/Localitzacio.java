package com.example.bcnet_app.models;

import com.google.gson.annotations.SerializedName;

public class Localitzacio {
    private String title;
    private String imageUrl;
    private String content;
    String category;
    String semaforUrl;

    //Exemple d'us de retrofit
    @SerializedName("body")
    private String text;

    public String getText() {
        return text;
    }
    //Fi exemple retrofit
    public Localitzacio(String imageUrl, String titol, String content, String category, String semaforUrl) {
        this.title = titol;
        this.imageUrl = imageUrl;
        this.content = content;
        this.category = category;
        this.semaforUrl = semaforUrl;
    }

    public Localitzacio() {
    }
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title=title;}
    public String getImageUrl () { return imageUrl;}
    public void setImageUrl (String imageUrl) {this.imageUrl = imageUrl;}
    public String getContent () { return content;}
    public void setContent (String content) {this.content = content;}
    public String getCategory () { return category;}
    public void setCategory (String content) {this.category = category;}
    public String getSemaforUrl () { return semaforUrl;}
    public void setSemaforUrl (String content) {this.semaforUrl = semaforUrl;}
}

