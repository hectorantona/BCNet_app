package com.example.bcnet_app.models;
//import com.google.firebase.database.ServerValue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("result")
    @Expose
    private String correcte;
    @SerializedName("error")
    @Expose
    private String msg;

    @SerializedName("user")
    @Expose
    private String usuari;
    @SerializedName("text")
    @Expose
    private String comentari;

    private String userimg;

    private Float puntuacio;
    private Integer likes;
    private Object timestamp;

    public Comment(String usuari, String comentari, Float puntuacio) {
        this.usuari = usuari;
        this.comentari = comentari;
        this.puntuacio = puntuacio;

        this.likes = 0;
        //this.timestamp = ServerValue.TIMESTAMP;
    }

    public Comment(String usuari, String comentari, Float puntuacio, Object timestamp) {
        this.usuari = usuari;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.likes = 0;
        //this.timestamp = timestamp;
    }

    public String getCorrecte() {
        return correcte;
    }

    public String getMsg() {
        return msg;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public void setPuntuacio(Float puntuacio) {
        this.puntuacio = puntuacio;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getComentari() {
        return comentari;
    }

    public Float getPuntuacio() {
        return puntuacio;
    }

    public Integer getLikes() {
        return likes;
    }
}
