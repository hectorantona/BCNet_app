package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.Comentari;
import com.example.bcnet_app.repositories.ComentariRepository;

import java.util.List;

public class CommentViewModel extends ViewModel {
    //es mutable perque es pugui modificar, sino nomes es podria mirar pero no canviar
    //private LiveData<Localitzacio> localitzacioLiveData;

    private MutableLiveData<List<Comentari>> mComentari;
    private ComentariRepository Repo;

    /*public MainActivity2ViewModel(@NonNull Application application) {
        super(application);
    }*/

    public void init () {
       /* if (localitzacioLiveData != null) {
            return;
        }*/
        if (mComentari != null) {
            return;
        }

        Repo = new ComentariRepository();
        //localitzacioLiveData = Repo.getLocalitzacioLiveData();
        mComentari = Repo.getComentaris();
    }

    public LiveData<List<Comentari>> getComentaris () { return mComentari;}

    /*
    public void searchLocalitzacions (String keyword, String name) {
        //Aixo es per agafar la apikey
        //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
        Repo.SearchLocalitzacio(keyword, name, "aaaaa");

    }
  */
}