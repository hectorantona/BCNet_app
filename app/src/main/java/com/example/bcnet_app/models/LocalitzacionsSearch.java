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
            Log.d("Searchloc", "localitzacio" + i + " " + localitzacions.get(i).getName());
            if (localitzacions.get(i).getName().equals(name)) {

                return localitzacions.get(i);
            }

        }
        Log.d("Searchloc", "FAILL");
        return null;
    }
    public Integer getnumelements() {
        return localitzacions.size();
    }
}
