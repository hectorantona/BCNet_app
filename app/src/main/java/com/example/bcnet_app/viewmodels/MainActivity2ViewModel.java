package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.LocalitzacioRespository;

public class MainActivity2ViewModel extends ViewModel {

    private static final String TAG = "MainActivity2ViewModel";
    private LiveData<LocalitzacionsSearch> localitzacionsSearchLiveData;
    private LiveData<LocalitzacionsSearch> localitzacionsPrefSearchLiveData;
    private LocalitzacioRespository Repo;

    /*public MainActivity2ViewModel(@NonNull Application application) {
        super(application);
    }*/

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


    public void searchLocalitzacions (String name) {
        LocalitzacioRespository.getInstance().searchLocalitzacio(name);
    }
    public void searchAllLocalitzacions () {
        LocalitzacioRespository.getInstance().searchAllLocalitzacio();
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
