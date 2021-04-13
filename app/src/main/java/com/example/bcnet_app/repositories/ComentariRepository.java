package com.example.bcnet_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.models.Comentari;

import java.util.ArrayList;
import java.util.List;


public class ComentariRepository {
   // private static final String LOCALITZACIO_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";
    //URL de la API, aquesta es un exemple

    //Singleton patern
    private static ComentariRepository instance;

    public static ComentariRepository getInstance() {
        if (instance == null) {
            instance = new ComentariRepository();
        }
        return instance;
    }
/*
    private LocalitzacioSearchService localitzacioSearchService;
    private MutableLiveData<Localitzacio> localitzacioLiveData;

    public LocalitzacioRespository () {
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
    public void SearchLocalitzacio(String keyword, String name, String apiKey) {
        localitzacioSearchService.searchLocalitzacio(keyword, name, apiKey)
                .enqueue(new Callback<Localitzacio>() {
                    @Override
                    public void onResponse(Call<Localitzacio> call, Response<Localitzacio> response) {
                        if (response.body() != null) {
                            localitzacioLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Localitzacio> call, Throwable t) {
                        localitzacioLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Localitzacio> getLocalitzacioLiveData() {
        return localitzacioLiveData;
    }*/

    private ArrayList<Comentari> dataSet = new ArrayList<>();

    public MutableLiveData<List<Comentari>> getComentaris () {
        setLocalitzacions();

        MutableLiveData<List<Comentari>> data  = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }



    private void setLocalitzacions () {
        dataSet = new ArrayList<>();
        Comentari c = new Comentari("Adrià", "comenari 1", (float) 4.0, "1");
        dataSet.add(c);
    }
}
