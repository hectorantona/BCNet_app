package com.example.bcnet_app.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.LocalitzacioRespository;
import com.example.bcnet_app.response.InfoLocalitzacioResponse;
import com.example.bcnet_app.response.NewLocalitzacioResponse;
import com.example.bcnet_app.response.allLocalitzacionsResponse;

public class MainActivity2ViewModel extends ViewModel {

    private static final String TAG = "MainActivity2ViewModel";
    private LiveData<LocalitzacionsSearch> localitzacionsSearchLiveData;
    private LiveData<LocalitzacionsSearch> localitzacionsPrefSearchLiveData;
    private LocalitzacioRespository Repo;



    public void init () {
        localitzacionsSearchLiveData = LocalitzacioRespository.getInstance().getlocalitzacions();
    }

    public void initPref() {
        localitzacionsPrefSearchLiveData = LocalitzacioRespository.getInstance().getpreflocalitzacions();
    }

    public LiveData<LocalitzacionsSearch> getLocalitzacions () {
        return localitzacionsSearchLiveData;
    }

    public LiveData<LocalitzacionsSearch> getPrefLocalitzacions () {
        return localitzacionsPrefSearchLiveData;
    }

    public void setLocalitzacioPref(String nom, String id) {
        LocalitzacioRespository.getInstance().afegirPreferits(nom, id);
        Log.d(TAG, "LIKES: " + localitzacionsPrefSearchLiveData.getValue().getnumelements());
    }

    public void setLocalitzacioUnpref(String nom, String id) {
        LocalitzacioRespository.getInstance().eliminarPreferits(nom, id);
    }

    public void newLocalitzacio(String nomloc, String direccio, String barri, String longitud, String latitud, String descripcio, String web, String img, String horari, String categoria, NewLocalitzacioResponse callback) {
        LocalitzacioRespository.getInstance().newlocalitzacio(nomloc, direccio, barri, longitud, latitud, descripcio, web, img, horari, categoria, callback);
    }

    public void searchLocalitzacio (String key, InfoLocalitzacioResponse callback) {
        LocalitzacioRespository.getInstance().searchLocalitzacio(key, callback);
    }
    public void searchAllLocalitzacions (allLocalitzacionsResponse callback) {
        LocalitzacioRespository.getInstance().searchAllLocalitzacio(callback);
    }

    public void searchPrefLocalitzacions (String name) {
        LocalitzacioRespository.getInstance().searchPrefLocalitzacio(name);
    }

    public void afegeixpuntuacio(String nomlocalitzacio, String nomuser, String puntuacio) {
        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        //Agafem l'id de la localització per crear el comment
        String id = l.getValue().getelembyname(nomlocalitzacio).getId();
        LocalitzacioRespository.getInstance().afegeixpuntuacio(id, nomuser, puntuacio);
    }



}
