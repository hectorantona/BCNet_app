package com.example.bcnet_app.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.api.UserService;
import com.example.bcnet_app.models.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class UserRepository {

    private static final String USER_SERVICE_BASE_URL = "https://us-central1-bcnet-backend.cloudfunctions.net/";
    private static final String TAG = "UserREPO";
    //Singleton patern
    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private UserService userService;
    private MutableLiveData<String> loginLiveData;
    private MutableLiveData<String> signupLiveData;

    private MutableLiveData<User> userLiveData;




    public UserRepository() {
        loginLiveData = new MutableLiveData<>();
        userLiveData = new MutableLiveData<>();
        signupLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //per fer debug
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        userService = new Retrofit.Builder()
                .baseUrl(USER_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

    //crida a l'api
    public void login(String username, String password) {
        userService.loginUser(username, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "LoginOK: " + response.body());
                            //loginLiveData.setValue(response.body());
                        }
                        else{
                            Log.d(TAG, "RespOKLoginBAD " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        loginLiveData.postValue(null);
                        Log.d(TAG, "Login CACA: " + t.getLocalizedMessage());

                    }
                });
    }

    //crida a l'api crear user
    public void signup(String email, String username, String password) {
        userService.createUser(email, username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    Log.d(TAG, "SignupOK: " + response.body());
                    //signupLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                signupLiveData.postValue(null);
                Log.d(TAG, "SignupCACA: ");


            }
        });
    }

    //Crida de l'usuari
}
