package com.example.bcnet_app.models;
//import com.google.firebase.database.ServerValue;

public class Comment {
    private String usuari;
    private String comentari;
    private Float puntuacio;
    private Integer likes;
    private String id;
    private Object timestamp;

    public Comment(String usuari, String comentari, Float puntuacio, String id) {
        this.usuari = usuari;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.id = id;
        this.likes = 0;
        //this.timestamp = ServerValue.TIMESTAMP;
    }

    public Comment(String usuari, String comentari, Float puntuacio, String id, Object timestamp) {
        this.usuari = usuari;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.id = id;
        this.likes = 0;
        //this.timestamp = timestamp;
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
