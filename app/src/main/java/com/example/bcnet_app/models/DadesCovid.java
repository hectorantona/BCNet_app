package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DadesCovid {

    @SerializedName("result")
    @Expose
    private String correcte;

    @SerializedName("error")
    @Expose
    private String msg;

    @SerializedName("username")
    @Expose
    private String usuari;

    @SerializedName("comentari")
    @Expose
    private String comentari;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("puntuacio")
    @Expose
    private String puntuacioCovid;

    @SerializedName("picture")
    @Expose
    private String usuariimg;

    @SerializedName("semafor")
    @Expose
    private String semaforcoment;

    private boolean gelHidroalcoholic;
    private boolean distanciaSeguretat;
    private boolean termometre;
    private boolean mascareta;
    private static String dateFormat = "dd-MM-yyyy hh:mm";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public DadesCovid(String usuari, String puntuacioCovid, String comentaris, boolean gelHidroalcoholic, boolean distanciaSeguretat, boolean termometre, boolean mascareta) {
        this.usuari = usuari;
        this.puntuacioCovid = puntuacioCovid;
        this.comentari = comentaris;
        this.gelHidroalcoholic = gelHidroalcoholic;
        this.distanciaSeguretat = distanciaSeguretat;
        this.termometre = termometre;
        this.mascareta = mascareta;
    }

    private Float calcularPuntuacioCovid(){
        Float aux = (float)0;

        if (isTermometre()) aux = aux + 1;
        if (isDistanciaSeguretat()) aux = aux + 1;
        if (isGelHidroalcoholic()) aux = aux + 1;
        if (isMascareta()) aux = aux + 1;

        aux = (aux * 10)/4;
        return aux;
    }

    public String ConvertMilliSecondsToFormattedDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));
        return simpleDateFormat.format(calendar.getTime());
    }



    public String getPuntuacioGlobal () {
        return calcularPuntuacioCovid().toString();
        //return "6.3";
    }
    public void setPuntuacioCovid(String puntuacioCovid) {
        this.puntuacioCovid = puntuacioCovid;
    }

    public boolean isGelHidroalcoholic() {
        return gelHidroalcoholic;
    }
    public void setGelHidroalcoholic(boolean gelHidroalcoholic) {
        this.gelHidroalcoholic = gelHidroalcoholic;
    }

    public boolean isDistanciaSeguretat() {
        return distanciaSeguretat;
    }
    public void setDistanciaSeguretat(boolean distanciaSeguretat) {
        this.distanciaSeguretat = distanciaSeguretat;
    }

    public boolean isTermometre() {
        return termometre;
    }
    public void setTermometre(boolean termometre) {
        this.termometre = termometre;
    }

    public boolean isMascareta() {
        return mascareta;
    }
    public void setMascareta(boolean mascareta) {
        this.mascareta = mascareta;
    }

    public String getUsuari() { return usuari; }

    public String getComentari() { return comentari; }

    public String getCorrecte() { return correcte; }

    public String getDate() { return ConvertMilliSecondsToFormattedDate(); }

    public String getUsuariimg() {return usuariimg; }

    public String getUsuarisemafor(){return semaforcoment;}


}
