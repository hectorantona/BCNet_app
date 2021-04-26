package com.example.bcnet_app.api;

import com.example.bcnet_app.models.Localitzacio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalitzacioSearchService {
    @GET("existsEstablimentDB")
    Call<Localitzacio> searchLocalitzacio(
            //Aqui hem de posar el que farem servir a les crides, a l'exemple era una key word, l'autor i l'Apikey
           // @Query("q") String query, //keyword
            @Query("nom") String name
    );

    @GET("allEstablimentsDB")
    Call<Localitzacio> allLocalitzacions(
            //Aqui hem de posar el que farem servir a les crides, a l'exemple era una key word, l'autor i l'Apikey
    );
}
