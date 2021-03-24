package com.example.bcnet_app;

import com.example.bcnet_app.models.Localitzacio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    /*El get es per agafar infor del servidor, la fake api te post, pero nos haurem de posar localitzacions o el que sigui
    https...../posts nosaltres tindrem https...../localitzacions */
    @GET("posts")
    Call<List<Localitzacio>> getLoca();
}
