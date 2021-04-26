package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacioResponse;
import com.example.bcnet_app.repositories.LocalitzacioRespository;

public class MainActivity2ViewModel extends ViewModel {

    private static final String TAG = "MainActivity2ViewModel";
    private LiveData<LocalitzacioResponse> localitzacioResponseLiveData;
    private LiveData<Localitzacio> localitzacioLiveData;

    //private MutableLiveData<List<Localitzacio>> mLocalitzacions;
    private LocalitzacioRespository Repo;

    /*public MainActivity2ViewModel(@NonNull Application application) {
        super(application);
    }*/

    public void init () {
        /*if (localitzacioResponseLiveData != null) {
            return;
        }*/
        if (localitzacioLiveData != null) {
            return;
        }
        /*if (mLocalitzacions != null) {
            return;
        }*/

        Repo = new LocalitzacioRespository();
        //localitzacioResponseLiveData = Repo.getLocalitzacioResponseLiveData();
        localitzacioLiveData = Repo.getLocalitzacioLiveData();
       // Log.d(TAG, "onClick: clicked on: " + localitzacioResponseLiveData.getValue());
        //mLocalitzacions = Repo.getLocalitzacions();
    }

    public LiveData<LocalitzacioResponse> getLocalitzacionsResponse () {
        return localitzacioResponseLiveData;}

    public LiveData<Localitzacio> getLocalitzacions () {
        return localitzacioLiveData;}


    public void searchLocalitzacions (String name) {
        Repo.searchLocalitzacio(name);
    }

}
