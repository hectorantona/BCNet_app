package com.example.bcnet_app.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.repositories.ComentariRepository;

public class CommentViewModel extends ViewModel {
    //es mutable perque es pugui modificar, sino nomes es podria mirar pero no canviar
    //private LiveData<Localitzacio> localitzacioLiveData;

    private static final String TAG = "CommentViewModel";
    private LiveData<CommentResponse> mComentari;
    private ComentariRepository Repo;
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

        Repo = new ComentariRepository();
        //localitzacioLiveData = Repo.getLocalitzacioLiveData();
        mComentari = Repo.getcomments();
    }

    public LiveData<CommentResponse> getComentaris () { return mComentari;}

    public void searchComments (String nomlocalitzacio) {
        Repo.searchComments(nomlocalitzacio);
    }
    public void newComment (String nomlocalitzacio, String nomuser, String comment) {
        Log.d(TAG, "Anem a crear el comment" + comment + nomlocalitzacio);

        Repo.newComment(nomlocalitzacio, nomuser, comment);
    }
}