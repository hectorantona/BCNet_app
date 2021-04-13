package com.example.bcnet_app.models;

public class DadesCovid {
    private Integer puntuacioCovid;
    private String comentaris;
    private boolean gelHidroalcoholic;
    private boolean distanciaSeguretat;
    private boolean termometre;

    public DadesCovid(Integer puntuacioCovid, String comentaris, boolean gelHidroalcoholic, boolean distanciaSeguretat, boolean termometre) {
        this.puntuacioCovid = puntuacioCovid;
        this.comentaris = comentaris;
        this.gelHidroalcoholic = gelHidroalcoholic;
        this.distanciaSeguretat = distanciaSeguretat;
        this.termometre = termometre;
    }

    public Integer getPuntuacioCovid() {
        return 3; //CANVIAR AIXO HARDCODED
    }
    public void setPuntuacioCovid(Integer puntuacioCovid) {
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


}
