package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.repositories.LocalitzacioRespository;

import java.util.List;

public class MainActivity2ViewModel extends ViewModel {
    //es mutable perque es pugui modificar, sino nomes es podria mirar pero no canviar
    //private LiveData<Localitzacio> localitzacioLiveData;

    private MutableLiveData<List<Localitzacio>> mLocalitzacions;
    private LocalitzacioRespository Repo;

    /*public MainActivity2ViewModel(@NonNull Application application) {
        super(application);
    }*/

    public void init () {
       /* if (localitzacioLiveData != null) {
            return;
        }*/
        if (mLocalitzacions != null) {
            return;
        }

        Repo = new LocalitzacioRespository();
        //localitzacioLiveData = Repo.getLocalitzacioLiveData();
        mLocalitzacions = Repo.getLocalitzacions();
    }

    public LiveData<List<Localitzacio>> getLocalitzacions () { return mLocalitzacions;}

    /*
    public void searchLocalitzacions (String keyword, String name) {
        //Aixo es per agafar la apikey
        //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
        Repo.SearchLocalitzacio(keyword, name, "aaaaa");

    }
  */

}
