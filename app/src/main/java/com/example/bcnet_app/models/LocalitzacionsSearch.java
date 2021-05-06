package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocalitzacionsSearch {
    @SerializedName("result")
    @Expose
    public boolean error;
    @SerializedName("value")
    @Expose
    public ArrayList<Localitzacio> localitzacions;

    public LocalitzacionsSearch (){
        localitzacions = new ArrayList<>();
    }

    public LocalitzacionsSearch (ArrayList<Localitzacio> l){
        localitzacions = l;
    }
    public ArrayList<Localitzacio> getLocalitzacions() {
        return localitzacions;
    }

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

    public void filterByName(String name) {
        ArrayList<Localitzacio> reduced = new ArrayList<>();
        for (Localitzacio l : localitzacions) {
            if (l.getName().contains(name)) {
                reduced.add(l);
            }
        }
        localitzacions = reduced;
    }

    public void filterByCategory(String name) {
        ArrayList<Localitzacio> reduced = new ArrayList<>();
        for (Localitzacio l : localitzacions) {
            if (l.getCategory().contains(name)) {
                reduced.add(l);
            }
        }
        localitzacions = reduced;
    }
}
