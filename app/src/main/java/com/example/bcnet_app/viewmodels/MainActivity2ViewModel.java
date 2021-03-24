package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.repositories.LocalitzacioRespository;

import java.util.List;

public class MainActivity2ViewModel extends ViewModel {
    //es mutable perque es pugui modificar, sino nomes es podria mirar pero no canviar
    private MutableLiveData<List<Localitzacio>> mLocalitzacions;
    private LocalitzacioRespository mRepo;

    public void init () {
        if (mLocalitzacions != null) {
            return;
        }
        mRepo = LocalitzacioRespository.getInstance();
        mLocalitzacions = mRepo.getLocalitzacions();
    }

    public LiveData<List<Localitzacio>> getLocalitzacions () { return mLocalitzacions;}


}
