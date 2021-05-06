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
    public Localitzacio getelembyname (String name) {
        for (int i = 0; i < localitzacions.size(); ++i) {
            if (localitzacions.get(i).getName() == name)
                return localitzacions.get(i);

            }
        return null;
    }
    public Integer getnumelements() {
        return localitzacions.size();
    }
}
