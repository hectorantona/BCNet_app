package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Localitzacio {
    /*Per fer la prova en retornaran el name i la categoria
    {
    "name" : "Bar Paco",
    "category" : "restaurant"
    }
    */

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("categoria")
    @Expose
    private String category;

    private String imageUrl;
    private String content;
    private String puntuacioGlobal;
    private ArrayList<Valoracio> valoracions;

    private String semaforUrl;



    //public String getText() {
    //    return text;
    //}
    //Fi exemple retrofit
    public Localitzacio(String imageUrl, String name, String content, String category, String semaforUrl) {
        this.name = name;
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
    public String getName() {return name;}
    public void setName(String title) {this.name=title;}
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

