package com.example.bcnet_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.api.LocalitzacioSearchService;
import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacionsSearch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LocalitzacioRespository {
    private static final String LOCALITZACIO_SEARCH_SERVICE_BASE_URL = "https://us-central1-bcnet-backend.cloudfunctions.net/";
    private static final String TAG = "REPO";
    //Singleton patern
    private static LocalitzacioRespository instance;

    public static LocalitzacioRespository getInstance() {
        if (instance == null) {
            instance = new LocalitzacioRespository();
        }
        return instance;
    }

    private LocalitzacioSearchService localitzacioSearchService;
    private MutableLiveData<LocalitzacionsSearch> localitzacionsSearchLiveData;
    private MutableLiveData<Localitzacio> localitzacioLiveData;


//ha de ser privat
    private LocalitzacioRespository() {
        localitzacionsSearchLiveData = new MutableLiveData<>();
        localitzacioLiveData = new MutableLiveData<>();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //per fer debug
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        localitzacioSearchService = new Retrofit.Builder()
                .baseUrl(LOCALITZACIO_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LocalitzacioSearchService.class);
    }

    //crida a l'api
    public void searchLocalitzacio(String name) {
        localitzacioSearchService.searchLocalitzacio(name)
                .enqueue(new Callback<Localitzacio>() {
                    @Override
                    public void onResponse(Call<Localitzacio> call, Response<Localitzacio> response) {
                        if (response.body() != null) {
                            Localitzacio l = response.body();

                            Log.d(TAG, "CORRECTE: " + response.body());
                            Log.d(TAG, "Localitzacio: " + l.getName());
                            localitzacioLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Localitzacio> call, Throwable t) {
                        Log.d(TAG, "Fail: " + localitzacioLiveData.getValue());
                        localitzacioLiveData.postValue(null);
                    }
                });
    }

    public void searchAllLocalitzacio() {
        localitzacioSearchService.allLocalitzacions()
                .enqueue(new Callback<LocalitzacionsSearch>() {
                    @Override
                    public void onResponse(Call<LocalitzacionsSearch> call, Response<LocalitzacionsSearch> response) {
                        if (response.body() != null) {
                            //LocalitzacioResponse l = response.body();
                            localitzacionsSearchLiveData.postValue(response.body());
                            Log.d(TAG, "CORRECTE: ha funcionat" + localitzacionsSearchLiveData.getValue().getnumelements());


                        }
                    }

                    @Override
                    public void onFailure(Call<LocalitzacionsSearch> call, Throwable t) {
                        Log.d(TAG, "Fail: " + localitzacioLiveData.getValue());
                        localitzacionsSearchLiveData.postValue(null);
                    }
                });
    }

    public LiveData<LocalitzacionsSearch> getlocalitzacions() {
        Log.d(TAG, "onClick: clicked on: " + localitzacionsSearchLiveData.getValue());

        return localitzacionsSearchLiveData;
    }

    public LiveData<Localitzacio> getLocalitzacioLiveData() {
        Log.d(TAG, "onClick: clicked on: " + localitzacioLiveData.getValue());
        //Localitzacio l = new Localitzacio("a", "Museu", "aa", "a", "a");
        //localitzacioLiveData.postValue(l);
        return localitzacioLiveData;
    }
}

