package com.example.bcnet_app.api;

import com.example.bcnet_app.models.LocalitzacionsSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalitzacioService {
    @GET("existsEstablimentkeyDB")
    Call<LocalitzacionsSearch> searchLocalitzaciobykey(
            @Query("key") String qkey
    );

    @GET("allEstablimentsDB")
    Call<LocalitzacionsSearch> allLocalitzacions(
    );

    //Canviar el tipo de classe amb la que es crida, o fe nomes una funcio que posi el comentari i la puntuacio
    @POST("afegirPuntuacionsEstablimentDB")
    Call<LocalitzacionsSearch> afegirpuntuacio(
    );

}
