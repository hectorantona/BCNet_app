package com.example.bcnet_app.models;

public class Valoracio {
    private String usuari;
    private String comentari;
    private Integer puntuacio;
    private Integer likes;

    public Valoracio(String usuari, String comentari, Integer puntuacio) {
        this.usuari = usuari;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.likes = 0;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public void setPuntuacio(Integer puntuacio) {
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

    public Integer getPuntuacio() {
        return puntuacio;
    }

    public Integer getLikes() {
        return likes;
    }
}
