package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.LocalitzacioRespository;

public class MainActivity2ViewModel extends ViewModel {

    private static final String TAG = "MainActivity2ViewModel";
    private LiveData<LocalitzacionsSearch> localitzacionsSearchLiveData;
    private LiveData<Localitzacio> localitzacioLiveData;

    //private MutableLiveData<List<Localitzacio>> mLocalitzacions;
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

        Repo = new LocalitzacioRespository();
        localitzacionsSearchLiveData = Repo.getlocalitzacions();
    }

    public LiveData<LocalitzacionsSearch> getLocalitzacions () {
        return localitzacionsSearchLiveData;}

   /* public LiveData<Localitzacio> getLocalitzacions () {
        return localitzacioLiveData;}*/



    public void searchLocalitzacions (String name) {
        Repo.searchLocalitzacio(name);
    }
    public void searchAllLocalitzacions () {
        Repo.searchAllLocalitzacio();
    }

}
