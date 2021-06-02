package com.example.bcnet_app.models;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    @SerializedName("latitud")
    @Expose
    private Float latitud;

    @SerializedName("longitud")
    @Expose
    private Float longitud;

    @SerializedName("descripcio")
    @Expose
    private String content;

    @SerializedName("puntuacio")
    @Expose
    private Float puntuacio;

    @SerializedName("horari")
    @Expose
    private String horari;

    @SerializedName("semafor")
    @Expose
    private String semaforUrl;

    private ArrayList<Comment> valoracions;
    private Integer puntuacioCOVID;

    public Localitzacio(String imageUrl, String name, String content, String category, String direccio) {
        this.nom = name;
        this.imageUrl = imageUrl;
        this.content = content;
        this.category = category;
        this.direccio = direccio;

    }

    public Localitzacio() {
    }

    public Float getLatitud() {
        return latitud;
    }

    public Float getLongitud() {
        return longitud;
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

    public Integer getPuntuacioGlobal () {
        int r = Math.round(puntuacio);
        return r;
    }

    public String getHorari () { return horari;}
    public void setHorari (String horari) {this.horari = horari;}

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

    public Integer getPuntuacioCOVID() { return puntuacioCOVID; }
    public void setPuntuacioCOVID(Integer puntuacioCOVID) { this.puntuacioCOVID = puntuacioCOVID; }

    public boolean hasPCov() {
        return puntuacioCOVID != null;
    }

    private boolean isNowBetweenDateTime(final Date s, final Date e)
    {
        final Date now = new Date();
        return now.after(s) && now.before(e);
    }

    private ArrayList<Date> dateFromHourMinSec(final String hhmmss)
    {
        if (hhmmss.matches("^[0-2][0-9]:[0-5][0-9]-[0-2][0-9]:[0-5][0-9]$"))
        {
            final String[] hm2 = hhmmss.split("-");
            final String[] hm2open = hm2[0].split(":");
            final GregorianCalendar gc1 = new GregorianCalendar();
            gc1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm2open[0]));
            gc1.set(Calendar.MINUTE, Integer.parseInt(hm2open[1]));

            final String[] hm2close = hm2[1].split(":");
            final GregorianCalendar gc2 = new GregorianCalendar();
            gc2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm2close[0]));
            gc2.set(Calendar.MINUTE, Integer.parseInt(hm2close[1]));

            ArrayList<Date> dates =  new ArrayList<>();;
            dates.add(gc1.getTime());
            dates.add(gc2.getTime());

            return dates;
        }
        else
        {
            throw new IllegalArgumentException(hhmmss + " is not a valid time, expecting HH:MM format");
        }
    }

    public boolean isopen(String horariLoc) {
        ArrayList<Date> dates =  dateFromHourMinSec(horariLoc);
        Log.d("isOPEN", "date open: " + dates.get(0) + "date closed: " + dates.get(1));
        return isNowBetweenDateTime(dates.get(0), dates.get(1));
    }
}



