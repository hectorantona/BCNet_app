package com.example.bcnet_app.models;

public class DadesCovid {
    private String puntuacioCovid;
    private String comentaris;
    private boolean gelHidroalcoholic;
    private boolean distanciaSeguretat;
    private boolean termometre;
    private boolean mascareta;

    public DadesCovid(String puntuacioCovid, String comentaris, boolean gelHidroalcoholic, boolean distanciaSeguretat, boolean termometre, boolean mascareta) {
        this.puntuacioCovid = puntuacioCovid;
        this.comentaris = comentaris;
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
}
