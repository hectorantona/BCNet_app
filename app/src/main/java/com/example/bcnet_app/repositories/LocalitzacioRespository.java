package com.example.bcnet_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.api.LocalitzacioService;
import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacioResponse;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.response.InfoLocalitzacioResponse;
import com.example.bcnet_app.response.NewLocalitzacioResponse;

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

    private LocalitzacioService localitzacioService;
    private MutableLiveData<LocalitzacionsSearch> localitzacionsSearchLiveData;
    private MutableLiveData<LocalitzacionsSearch> localitzacionsPrefSearchLiveData;
    private MutableLiveData<Localitzacio> localitzacioLiveData;


    //ha de ser privat
    private LocalitzacioRespository() {
        localitzacionsSearchLiveData = new MutableLiveData<>();
        localitzacioLiveData = new MutableLiveData<>();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //per fer debug
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        localitzacioService = new Retrofit.Builder()
                .baseUrl(LOCALITZACIO_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LocalitzacioService.class);
    }

    //crida a l'api
    public void searchLocalitzacio(String key, InfoLocalitzacioResponse callback) {
        localitzacioService.searchLocalitzacio(key)
                .enqueue(new Callback<LocalitzacionsSearch>() {
                    @Override
                    public void onResponse(Call<LocalitzacionsSearch> call, Response<LocalitzacionsSearch> response) {
                        if (response.body() != null) {
                            //Log.d(TAG, "Fail: " + response.body().getelemi(0).getName());
                            callback.infolocalitzacio(response.body().getelemi(0).getPuntuacioGlobal());
                        }
                    }

                    @Override
                    public void onFailure(Call<LocalitzacionsSearch> call, Throwable t) {
                        t.getCause();
                        Log.d(TAG, "Fail: " + t);
                        localitzacioLiveData.postValue(null);
                    }
                });
    }
    public void newlocalitzacio(String nomloc, String direccio, String barri, String descripcio, String web, String img, String horari, String categoria, String longitud, String latitud, NewLocalitzacioResponse callback) {
        localitzacioService.newLocalitzacio(nomloc, direccio, barri, descripcio, web, img, horari,categoria, longitud, latitud)
                .enqueue(new Callback<LocalitzacioResponse>() {
                    @Override
                    public void onResponse(Call<LocalitzacioResponse> call, Response<LocalitzacioResponse> response) {
                        if (response.body() != null) {
                            callback.newlocalitzacio(response.body().getError().equals("true"));
                        }
                    }

                    @Override
                    public void onFailure(Call<LocalitzacioResponse> call, Throwable t) {
                        Log.d(TAG, "Fail: " + localitzacioLiveData.getValue());
                        localitzacionsSearchLiveData.postValue(null);
                    }
                });
    }
    public void searchAllLocalitzacio() {
        localitzacioService.allLocalitzacions()
                .enqueue(new Callback<LocalitzacionsSearch>() {
                    @Override
                    public void onResponse(Call<LocalitzacionsSearch> call, Response<LocalitzacionsSearch> response) {
                        if (response.body() != null) {
                            //LocalitzacioResponse l = response.body();
                            localitzacionsSearchLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<LocalitzacionsSearch> call, Throwable t) {
                        Log.d(TAG, "Fail: " + localitzacioLiveData.getValue());
                        localitzacionsSearchLiveData.postValue(null);
                    }
                });
    }

    public void searchPrefLocalitzacio(String name) {
        localitzacioService.prefLocalitzacions(name)
                .enqueue(new Callback<LocalitzacionsSearch>() {
                    @Override
                    public void onResponse(Call<LocalitzacionsSearch> call, Response<LocalitzacionsSearch> response) {
                        if (response.body() != null) {
                            //LocalitzacioResponse l = response.body();
                            localitzacionsPrefSearchLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<LocalitzacionsSearch> call, Throwable t) {
                        Log.d(TAG, "Fail: " + localitzacioLiveData.getValue());
                        localitzacionsPrefSearchLiveData.postValue(null);
                    }
                });
    }

    public void afegirPreferits(String name, String id) {
        localitzacioService.setPreferit(name, id).enqueue(new Callback<Localitzacio>() {
            @Override
            public void onResponse(Call<Localitzacio> call, Response<Localitzacio> response) {
                Log.d(TAG, "Liked OK: " + response.body());
            }

            @Override
            public void onFailure(Call<Localitzacio> call, Throwable t) {
                Log.d(TAG, "Liked Not-OK");
            }
        });
    }

    public void eliminarPreferits(String nom, String id) {
        localitzacioService.unsetPreferit(nom, id).enqueue(new Callback<Localitzacio>() {
            @Override
            public void onResponse(Call<Localitzacio> call, Response<Localitzacio> response) {
                Log.d(TAG, "Unliked OK: " + response.body());
            }

            @Override
            public void onFailure(Call<Localitzacio> call, Throwable t) {

            }
        });
    }





    public void afegeixpuntuacio(String id, String nomuser, String puntuacio) {
        localitzacioService.allLocalitzacions()
                .enqueue(new Callback<LocalitzacionsSearch>() {
                    @Override
                    public void onResponse(Call<LocalitzacionsSearch> call, Response<LocalitzacionsSearch> response) {
                        if (response.body() != null) {
                            //LocalitzacioResponse l = response.body();
                            localitzacionsSearchLiveData.postValue(response.body());
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
        return localitzacionsSearchLiveData;
    }

    public LiveData<LocalitzacionsSearch> getpreflocalitzacions() {
        return localitzacionsPrefSearchLiveData;
    }

    public LiveData<Localitzacio> getLocalitzacioLiveData() {
        //Localitzacio l = new Localitzacio("a", "Museu", "aa", "a", "a");
        //localitzacioLiveData.postValue(l);
        return localitzacioLiveData;
    }

}


