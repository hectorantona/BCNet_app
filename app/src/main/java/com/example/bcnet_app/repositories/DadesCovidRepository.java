package com.example.bcnet_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.ViewLocalitzacio;
import com.example.bcnet_app.api.CommentService;
import com.example.bcnet_app.api.DadesCovidService;
import com.example.bcnet_app.models.Comment;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.DadesCovid;
import com.example.bcnet_app.models.DadesCovidResponse;
import com.example.bcnet_app.response.newCommentResponse;
import com.example.bcnet_app.response.newCovidCommentResponse;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DadesCovidRepository {
    private static final String DADES_COVID_SEARCH_SERVICE_BASE_URL = "https://us-central1-bcnet-backend.cloudfunctions.net/";
    private static final String TAG = "REPO COVID";

    private static DadesCovidRepository instance;

    public static DadesCovidRepository getInstance() {
        if (instance == null) {
            instance = new DadesCovidRepository();
        }
        return instance;
    }

    private ViewLocalitzacio view;
    private DadesCovidService dadesCovidService;
    private MutableLiveData<DadesCovidResponse> covidCommentResponseLiveData;
    private MutableLiveData<com.example.bcnet_app.models.DadesCovid> dadesCovidCommentLiveData;
    private String error = "true";

    private DadesCovidRepository() {

        view = new ViewLocalitzacio();
        covidCommentResponseLiveData = new MutableLiveData<>();
        dadesCovidCommentLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        dadesCovidService = new Retrofit.Builder()
                .baseUrl(DADES_COVID_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DadesCovidService.class);
    }
    public void newCovidComment (String idlocalitzacio, String nomuser, String puntuacio, String comentari, newCovidCommentResponse callback) {
        dadesCovidService.newCovidComment(idlocalitzacio, nomuser, puntuacio, comentari)
                .enqueue(new Callback<com.example.bcnet_app.models.DadesCovid>() {
                    @Override
                    public void onResponse(Call<DadesCovid> call, Response<DadesCovid> response) {
                        if (response.body() != null) {
                            error = response.body().getCorrecte();
                            callback.updateCovidcomments(error.equals("true"));
                        }
                    }

                    @Override
                    public void onFailure(Call<DadesCovid> call, Throwable t) {
                        covidCommentResponseLiveData.postValue(null);
                    }
        });
    }

    public void searchCovidComments(String localitzaciokey) {
        dadesCovidService.searchcovidcomments(localitzaciokey)
                .enqueue(new Callback<DadesCovidResponse>() {
                    @Override
                    public void onResponse(Call<DadesCovidResponse> call, Response<DadesCovidResponse> response) {
                        if (response.body() != null) {
                            covidCommentResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<DadesCovidResponse> call, Throwable t) {
                        covidCommentResponseLiveData.postValue(null);
                    }
                });
    }

    private ArrayList<DadesCovid> dataSet = new ArrayList<>();

    public LiveData<DadesCovidResponse> getcovidcomments() {

        return covidCommentResponseLiveData;
    }

    public void deleteCovidComment (String nomuser, String idlocalitzacio) {
        dadesCovidService.deleteCovidComment(nomuser, idlocalitzacio)
                .enqueue(new Callback<DadesCovidResponse>() {
                    @Override
                    public void onResponse(Call<DadesCovidResponse> call, Response<DadesCovidResponse> response) {
                    }

                    @Override
                    public void onFailure(Call<DadesCovidResponse> call, Throwable t) {
                    }
                });
    }
}




