package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocalitzacionsSearch {
    @SerializedName("nom")
    @Expose
    public boolean error;
    @SerializedName("skey")
    @Expose
    public List<Localitzacio> skey;
}
