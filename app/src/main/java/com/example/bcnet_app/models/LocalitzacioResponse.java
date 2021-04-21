package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocalitzacioResponse {

    @SerializedName("localitzacions")
    @Expose
    List<Localitzacio> localitzacions = null;

    @SerializedName("totalItems")
    @Expose
    int totalItems;

    public List<Localitzacio> getLocalitzacions() {
        return localitzacions;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
