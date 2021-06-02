package com.example.bcnet_app.api;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacioResponse;
import com.example.bcnet_app.models.LocalitzacionsSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalitzacioService {
    @GET("existsEstablimentkeyDB")
    Call<LocalitzacionsSearch> searchLocalitzacio(
            @Query("key") String qkey
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
            @Query("username") String name
    );

    @POST("createPreferitDB")
    Call<Localitzacio> setPreferit(
            @Query("username") String name,
            @Query("key") String id
    );

    @POST("deletePreferitDB")
    Call<Localitzacio> unsetPreferit(
            @Query("username") String name,
            @Query("key") String id
    );

    @POST("createSugerenciaEstablimentDB")
    Call<LocalitzacioResponse> newLocalitzacio (
            @Query("nom") String qnom,
            @Query("direccio") String qdireccio,
            @Query("barri") String qbarri,
            @Query("descripcio") String qdescripcio,
            @Query("link") String qlink,
            @Query("fotografia") String qfotografia,
            @Query("horari") String qhorari,
            @Query("categoria") String qcategoria,
            @Query("longitud") String qlongitud,
            @Query("latitud") String qlatitud
    );
}
