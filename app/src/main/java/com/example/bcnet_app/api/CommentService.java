package com.example.bcnet_app.api;

import com.example.bcnet_app.models.CommentResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommentService {

    @POST("createComentariDB")
    Call<com.example.bcnet_app.models.Comment> newComment(
            @Query("username") String qusername,
            @Query("key") String qkey,
            @Query("comentari") String qcomentari
    );

    @GET("allCommentsEstablimentDB")
    Call<CommentResponse> searchcomments(
            @Query("key") String qkey
    );

    @DELETE("deleteComentariDB")
    Call<CommentResponse> deletecomment(
            @Query("username") String qusername,
            @Query("key") String qkey
    );
}
