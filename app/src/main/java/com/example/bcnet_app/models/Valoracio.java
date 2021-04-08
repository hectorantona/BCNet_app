package com.example.bcnet_app.models;

public class Valoracio {
    private String usuari;
    private String comentari;
    private Float puntuacio;
    private Integer likes;

    public Valoracio(String usuari, String comentari, Float puntuacio) {
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
