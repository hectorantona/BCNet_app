package com.example.bcnet_app.api;

import com.example.bcnet_app.models.CommentResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Query;

public interface DadesCovidService {

    @DELETE("deleteDComentariDB") //PER FER MIRAR COMMENT FIREBASE NO ESTA IMPLEMENTADA!!!!!
    Call<CommentResponse> deletecomment(
            @Query("username") String qusername,
            @Query("key") String qkey
    );
}
