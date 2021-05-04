package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocalitzacionsSearch {
    @SerializedName("result")
    @Expose
    private boolean error;
    @SerializedName("valor")
    @Expose
    private ArrayList<Localitzacio> localitzacions;

    public LocalitzacionsSearch (){
        localitzacions = new ArrayList<>();
    }
    public ArrayList<Localitzacio> getLocalitzacions() {
        return localitzacions;
    }

    //search/sorting
    public Localitzacio getelemi (Integer i) {
       return localitzacions.get(i);
    }
    public Integer getnumelements() {
        return localitzacions.size();
    }
}
