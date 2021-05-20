package com.example.bcnet_app.api;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacionsSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalitzacioService {
    @GET("existsEstablimentDB")
    Call<Localitzacio> searchLocalitzacio(
            @Query("nom") String name
    );

    @GET("allEstablimentsDB")
    Call<LocalitzacionsSearch> allLocalitzacions(
    );

    //Canviar el tipo de classe amb la que es crida, o fe nomes una funcio que posi el comentari i la puntuacio
    @POST("afegirPuntuacionsEstablimentDB")
    Call<LocalitzacionsSearch> afegirpuntuacio(
    );

    @GET("viewPreferitsDB")
    Call<LocalitzacionsSearch> prefLocalitzacions(
            @Query("nom") String name
    );

    @POST("createEstablimentDB")
    Call<LocalitzacionsSearch> newLocalitzacio (
            @Query("nom") String qnom,
            @Query("direccio") String qdireccio,
            @Query("barri") String qbarri,
            //@Query("longitud") String qlongitud,
            //@Query("latitud") String qlatitud,
            @Query("descripcio") String qdescripcio,
            @Query("link") String qlink,
            @Query("fotografia") String qfotografia,
            @Query("horari") String qhorari,
            @Query("categoria") String qcategoria
    );
}
