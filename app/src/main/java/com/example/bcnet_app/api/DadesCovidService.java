package com.example.bcnet_app.api;

import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.DadesCovid;
import com.example.bcnet_app.models.DadesCovidResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DadesCovidService {

    @DELETE("deleteCovidComentariDB")
    Call<DadesCovidResponse> deleteCovidComment(
            @Query("username") String qusername,
            @Query("key") String qkey
    );

    @POST("afegirPAPerEstablimentDB")
    Call<DadesCovid> newCovidComment(
            @Query("key") String qkey,
            @Query("username") String qusername,
            @Query("puntuacio") String qpuntuacio,
            @Query("covidComment") String qcovidcomment
    );

    @GET("allCovidCommentsEstablimentDB")
    Call<DadesCovidResponse> searchcovidcomments(
            @Query("key") String qkey
    );
}
