package com.example.bcnet_app.repositories;

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
    private static final String TAG = "REPO";
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
    private MutableLiveData<User> userLiveData;

    public UserRepository() {
        loginLiveData = new MutableLiveData<>();
        userLiveData = new MutableLiveData<>();

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
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null) {
                            String s = response.body();

                            Log.d(TAG, "CORRECTE: " + response.body());
                            Log.d(TAG, "Loging: " + s);
                            loginLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        loginLiveData.postValue(null);
                    }
                });
    }

    //Crida de l'usuari
}
