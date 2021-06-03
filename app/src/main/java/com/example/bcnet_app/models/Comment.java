package com.example.bcnet_app.models;
//import com.google.firebase.database.ServerValue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Comment {
    @SerializedName("result")
    @Expose
    private String correcte;
    @SerializedName("error")
    @Expose
    private String msg;

    @SerializedName("username")
    @Expose
    private String usuari;

    @SerializedName("picture")
    @Expose
    private String usuariimg;

    @SerializedName("comentari")
    @Expose
    private String comentari;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("puntuacio")
    @Expose
    private Float puntuacio;

    private Integer likes;
    private String id;
    private Object timestamp;
    private static String dateFormat = "dd-MM-yyyy hh:mm";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public Comment(String usuari, String comentari, Float puntuacio, String id) {
        this.usuari = usuari;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.id = id;
        this.likes = 0;
    }

    public Comment(String usuari, String comentari, Float puntuacio, String id, Object timestamp) {
        this.usuari = usuari;
        this.comentari = comentari;
        this.puntuacio = puntuacio;
        this.id = id;
        this.likes = 0;
    }

    public String ConvertMilliSecondsToFormattedDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));
        return simpleDateFormat.format(calendar.getTime());
    }


    public String getUsuariimg() {return usuariimg; }

    public String getCorrecte() {
        return correcte;
    }

    public String getMsg() {
        return msg;
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

    public String getDate() { return ConvertMilliSecondsToFormattedDate(); }

    public Integer getLikes() {
        return likes;
    }
}
