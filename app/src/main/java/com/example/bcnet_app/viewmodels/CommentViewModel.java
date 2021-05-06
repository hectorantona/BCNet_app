package com.example.bcnet_app.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.ComentariRepository;
import com.example.bcnet_app.repositories.LocalitzacioRespository;

public class CommentViewModel extends ViewModel {
    //es mutable perque es pugui modificar, sino nomes es podria mirar pero no canviar
    //private LiveData<Localitzacio> localitzacioLiveData;

    private static final String TAG = "CommentViewModel";
    private LiveData<CommentResponse> mComentari;
    private String nomlocalitzacio;

    public CommentViewModel(String nomloca) {
        nomlocalitzacio = nomloca;
    }

    public void init () {
       /* if (localitzacioLiveData != null) {
            return;
        }*/
        if (mComentari != null) {
            return;
        }

        //localitzacioLiveData = Repo.getLocalitzacioLiveData();
        mComentari = ComentariRepository.getInstance().getcomments();
    }

    public LiveData<CommentResponse> getComentaris () { return mComentari;}

    public void searchComments (String nomlocalitzacio) {
        ComentariRepository.getInstance().searchComments(nomlocalitzacio);
    }
    public void newComment (String nomlocalitzacio, String nomuser, String comment) {
        //apliquem el patró singleton
        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();

        //Agafem l'id de la localització per crear el comment
        String id = l.getValue().getelembyname(nomlocalitzacio).getId();
        Log.d(TAG, "Anem a crear el comment" + comment + id);

        ComentariRepository.getInstance().newComment(id, nomuser, comment);
    }
}