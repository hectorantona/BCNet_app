package com.example.bcnet_app.api;

import com.example.bcnet_app.models.CommentResponse;

import org.w3c.dom.Comment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommentService {

    @POST("createComentariDB")
    Call<Comment> newComment(
            @Query("username") String qusername,
            @Query("key") String qkey,
            @Query("comentari") String qcomentari
    );

    @GET("allCommentsEstablimentDB")
    Call<CommentResponse> searchcomments(
            @Query("key") String qkey
    );
}
