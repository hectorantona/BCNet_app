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

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("direccio")
    @Expose
    private String direccio;


    private String content;

    @SerializedName("puntuacio")
    @Expose
    private String puntuacioGlobal;

    private ArrayList<Comment> valoracions;
    private Float puntuacioCOVID;

    private String semaforUrl;

    public Localitzacio(String imageUrl, String name, String content, String category, String direccio) {
        this.nom = name;
        this.imageUrl = imageUrl;
        this.content = content;
        this.category = category;
        this.direccio = direccio;

    }

    private Float calcularPuntuacioGlobal() {
        Float suma = (float)0;
        for (Comment c : valoracions) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return puntuacioGlobal;
    }

    public Float getPG () {
        //return calcularPuntuacioGlobal().toString();
        return Float.parseFloat("4.2");
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public Float getPuntuacioCOVID() { return puntuacioCOVID; }
    public void setPuntuacioCOVID(Float puntuacioCOVID) { this.puntuacioCOVID = puntuacioCOVID; }

}



