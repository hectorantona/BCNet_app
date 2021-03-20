package com.example.bcnet_app.models;

public class Localitzacio {
    private String title;
    private String imageUrl;

    public Localitzacio(String imageUrl, String titol) {
        this.title = titol;
        this.imageUrl = imageUrl;
    }

    public Localitzacio() {
    }
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title=title;}
    public String getImageUrl () { return imageUrl;}
    public void setImageUrl (String imageUrl) {this.imageUrl = imageUrl;}
}

