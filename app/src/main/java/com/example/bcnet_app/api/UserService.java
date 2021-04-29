package com.example.bcnet_app.api;

import com.example.bcnet_app.models.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("createUserDB")
    Call<User> createUser(
            //Aqui hem de posar el que farem servir a les crides, a l'exemple era una key word, l'autor i l'Apikey
            // @Query("q") String query, //keyword
            @Query("email") String qemail,
            @Query("username") String qusername,
            @Query("password") String qpassword
    );

}
