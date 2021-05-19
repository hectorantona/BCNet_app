package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DadesCovidResponse {

    @SerializedName("result")
    @Expose
    private boolean error;

    @SerializedName("value")
    @Expose
    private ArrayList<DadesCovid> dadesCovid;

    public DadesCovidResponse (){
        dadesCovid = new ArrayList<>();
    }
    public ArrayList<DadesCovid> getComentaris() {
        return dadesCovid;
    }

    public DadesCovid getelemi (Integer i) {
        return dadesCovid.get(i);
    }
    public Integer getnumelements() {
        return dadesCovid.size();
    }

}
