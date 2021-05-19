package com.example.bcnet_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.api.DadesCovidService;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.DadesCovid;
import com.example.bcnet_app.models.DadesCovidResponse;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DadesCovidRepository {
    private static final String DADES_COVID_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";
    //URL de la API, aquesta es un exemple

    //Singleton patern
    private static DadesCovidRepository instance;

    public static DadesCovidRepository getInstance() {
        if (instance == null) {
            instance = new DadesCovidRepository();
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

    private ArrayList<DadesCovid> dataSet = new ArrayList<>();

    public MutableLiveData<List<DadesCovid>> getDadesCovid () {
        setDadesCovid();

        MutableLiveData<List<DadesCovid>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setDadesCovid() {
        dataSet = new ArrayList<>();
        DadesCovid d = new DadesCovid("2", "Molt Higiènic ;), M'he sentit molt segur", true, true ,true, true);
        dataSet.add(d);
    }
    /*
    public void deletecomment (String nomuser, String idlocalitzacio) {
        DadesCovidService.deletecomment(nomuser, idlocalitzacio) //FALTA QUE BACK IMPLEMENTI AQUESTA FUNCIO
                .enqueue(new Callback<DadesCovidResponse>() {
                    @Override
                    public void onResponse(Call<DadesCovidResponse> call, Response<DadesCovidResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<DadesCovidResponse> call, Throwable t) {

                    }
                });


    }*/
}




