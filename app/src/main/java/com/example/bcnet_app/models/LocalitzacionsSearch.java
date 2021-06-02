package com.example.bcnet_app.models;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocalitzacionsSearch {
    @SerializedName("result")
    @Expose
    private boolean error;
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

    public boolean geterror() {
        return error;
    }

    public Localitzacio getelemi (Integer i) {
        return localitzacions.get(i);
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


    public void orderPuntuacioGlobal() {
        ArrayList<Localitzacio> ordered = new ArrayList<>();
        while (localitzacions.size() > 0) {
            Integer max = 0;
            int ind_max = 0;
            for(int i = 0; i < localitzacions.size(); i++) {
                if (localitzacions.get(i).getPuntuacioGlobal() >= max) {
                    ind_max = i;
                    max = localitzacions.get(i).getPuntuacioGlobal();
                }
            }
            ordered.add(localitzacions.get(ind_max));
            localitzacions.remove(ind_max);
        }
        localitzacions = ordered;
    }

    public void orderPuntuacioCovid() {
        ArrayList<Localitzacio> ordered = new ArrayList<>();
        int num_it = 0, size_of_loc = localitzacions.size();
        while (localitzacions.size() > 0 || num_it > size_of_loc) {
            num_it = num_it + 1;
            int max = 0;
            int ind_max = 0;
            for(int i = 0; i < localitzacions.size(); i++) {
                if (localitzacions.get(i).getPuntuacioCOVID() != null && localitzacions.get(i).getPuntuacioCOVID() >= max) {
                    ind_max = i;
                    max = localitzacions.get(i).getPuntuacioCOVID();
                }
            }
            ordered.add(localitzacions.get(ind_max));
            localitzacions.remove(ind_max);
        }
        localitzacions = ordered;
    }

    public Localitzacio getelembyname (String name) {
        for (int i = 0; i < localitzacions.size(); ++i) {
            Log.d("Searchloc", "localitzacio" + i + " " + localitzacions.get(i).getName());
            if (localitzacions.get(i).getName().equals(name)) {

                return localitzacions.get(i);
            }

        }
        Log.d("Searchloc", "FAIL");
        return null;
    }

    public void filterPref(ArrayList<Localitzacio> localitzacionsPref) {
        ArrayList<Localitzacio> reduced = new ArrayList<>();
        for (Localitzacio l : localitzacionsPref) {
            for (Localitzacio l2 : localitzacions) {
                if (l.getName().equals(l2.getName())) reduced.add(l2);
            }
        }
        localitzacions = reduced;
    }
}
