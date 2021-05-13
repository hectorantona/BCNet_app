package com.example.bcnet_app.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.ComentariRepository;
import com.example.bcnet_app.repositories.LocalitzacioRespository;

public class MainActivity2ViewModel extends ViewModel {

    private static final String TAG = "MainActivity2ViewModel";
    private LiveData<LocalitzacionsSearch> localitzacionsSearchLiveData;
    private LocalitzacioRespository Repo;


    /*public MainActivity2ViewModel(@NonNull Application application) {
        super(application);
    }*/

    public void init () {
        /*if (localitzacioResponseLiveData != null) {
            return;
        }
        if (localitzacioLiveData != null) {
            return;
        }*/

        localitzacionsSearchLiveData = LocalitzacioRespository.getInstance().getlocalitzacions();
        Log.d(TAG, "OKEYY: " );

    }

    public LiveData<LocalitzacionsSearch> getLocalitzacions () {
        return localitzacionsSearchLiveData;}

   /* public LiveData<Localitzacio> getLocalitzacions () {
        return localitzacioLiveData;}*/



    public void searchLocalitzacions (String name) {
        LocalitzacioRespository.getInstance().searchLocalitzacio(name);
    }
    public void searchAllLocalitzacions () {
        LocalitzacioRespository.getInstance().searchAllLocalitzacio();
    }
    public void afegeixpuntuacio(String nomlocalitzacio, String nomuser, String puntuacio) {
        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        //Agafem l'id de la localització per crear el comment
        String id = l.getValue().getelembyname(nomlocalitzacio).getId();
        LocalitzacioRespository.getInstance().afegeixpuntuacio(id, nomuser, puntuacio);
    }

}
