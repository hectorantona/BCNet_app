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

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("categoria")
    @Expose
    private String category;

    @SerializedName("fotografia")
    @Expose
    private String imageUrl;

    private String content;
    private Float puntuacioGlobal;
    private ArrayList<Comentari> valoracions;
    private Float puntuacioCOVID;

    private String semaforUrl;



    //public String getText() {
    //    return text;
    //}
    //Fi exemple retrofit
    public Localitzacio(String imageUrl, String name, String content, String category, String semaforUrl) {
        this.nom = name;
        this.imageUrl = imageUrl;
        this.content = content;
        this.category = category;
        this.semaforUrl = semaforUrl;

    }

    private Float calcularPuntuacioGlobal() {
        Float suma = (float)0;
        for (Comentari c : valoracions) {
            suma += c.getPuntuacio();
        }
        Float avg = (float)-1;
        if (valoracions.size() > 0) {
            avg = suma / valoracions.size();
        }
        return avg;
    }

    public Localitzacio() {
    }
    public String getName() {return nom;}
    public void setName(String title) {this.nom=title;}

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
        return "4.2";
    }

    public Float getPuntuacioCOVID() { return puntuacioCOVID; }
    public void setPuntuacioCOVID(Float puntuacioCOVID) { this.puntuacioCOVID = puntuacioCOVID; }
}

