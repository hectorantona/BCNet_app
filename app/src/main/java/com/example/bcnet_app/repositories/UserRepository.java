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
    private String loginusername;




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
    public void login(String username, String password, LoginResponse callback) {
        userService.loginUser(username, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "LoginOK: " + response.body());
                            loginusername = response.body().getUsername();
                            callback.login(response.body().getUsername(), response.body().getMessage().equals("true"), response.body().getErrormsg());
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(TAG, "Login CACA: " + t.getLocalizedMessage());
                        loginusername = null;
                    }
                });
    }

    //crida a l'api crear user
    public void signup(String email, String username, String password, SignUpResponse callback) {
        userService.createUser(email, username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "SignupOK: " + response.body());
                    signupLiveData.setValue(response.body().getUsername());
                    callback.signup(response.body().getUsername(), response.body().getMessage().equals("true"), response.body().getErrormsg());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                signupLiveData.postValue(null);
                Log.d(TAG, "SignupCACA: ");


            }
        });
    }

    //Crida l'api info user
    public void infouser(String username, InfoUserResponse callback) {
        userService.infoUser(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "InfoUserOK: " + response.body());
                    //loginLiveData.setValue(response.body().getEmail());
                    callback.infouser(response.body().getUsername(), response.body().getEmail(), response.body().getMessage().equals("true"), response.body().getErrormsg());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //loginLiveData.postValue(null);
                Log.d(TAG, "InfoUserCACA: ");


            }
        });
    }

    public void changePassword(String username, String oldpassword, String newpassword, ChangePswdResponse callback) {
        userService.changePassword(username, oldpassword, newpassword).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "ChangePasswordOK: " + response.body());
                    //loginLiveData.setValue(response.body().getPassword());
                    callback.changePassword(response.body().getUsername(), response.body().getPassword(), response.body().getMessage().equals("true"), response.body().getErrormsg());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //loginLiveData.postValue(null);
                Log.d(TAG, "ChangePasswordCACA: ");


            }
        });
    }
}
