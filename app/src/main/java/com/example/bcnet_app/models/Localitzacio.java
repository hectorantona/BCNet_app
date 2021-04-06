package com.example.bcnet_app.models;

import java.util.ArrayList;

public class Localitzacio {
    private String title;
    private String imageUrl;
    private String content;
    private String puntuacioGlobal;
    private ArrayList<Valoracio> valoracions;
    String category;
    String semaforUrl;

    //Exemple d'us de retrofit
    //@SerializedName("body")
    //private String text;

    //public String getText() {
    //    return text;
    //}
    //Fi exemple retrofit
    public Localitzacio(String imageUrl, String titol, String content, String category, String semaforUrl) {
        this.title = titol;
        this.imageUrl = imageUrl;
        this.content = content;
        this.category = category;
        this.semaforUrl = semaforUrl;
    }

    private Integer calcularPuntuacioGlobal() {
        Integer suma = 0;
        for (Valoracio v : valoracions) {
            suma += v.getPuntuacio();
        }
        Integer avg = -1;
        if (valoracions.size() > 0) {
            avg = suma / valoracions.size();
        }
        return avg;
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
    public String getPuntuacioGlobal () {
        //return calcularPuntuacioGlobal().toString();
        return "3";
    }
}

