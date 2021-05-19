package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.adapter.CommentAdapter;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.ComentariRepository;
import com.example.bcnet_app.repositories.LocalitzacioRespository;
import com.example.bcnet_app.response.newCommentResponse;

public class CommentViewModel extends ViewModel {
    //es mutable perque es pugui modificar, sino nomes es podria mirar pero no canviar
    //private LiveData<Localitzacio> localitzacioLiveData;

    private static final String TAG = "CommentViewModel";
    private LiveData<CommentResponse> mComentari;
    private CommentAdapter mAdapter;
    private String nomlocalitzacio;
    private String idlocalitzacio;

    public CommentViewModel(String nomloca) {
        nomlocalitzacio = nomloca;
    }

    public void init () {
       /* if (localitzacioLiveData != null) {
            return;
        }*/

        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        //Agafem l'id de la localització per crear el comment
        idlocalitzacio = l.getValue().getelembyname(nomlocalitzacio).getId();

        //localitzacioLiveData = Repo.getLocalitzacioLiveData();
        mComentari = ComentariRepository.getInstance().getcomments();
    }

    public LiveData<CommentResponse> getComentaris () { return mComentari;}

    public void searchComments () {
        ComentariRepository.getInstance().searchComments(idlocalitzacio);

    }

    public void newComment (String nomlocalitzacio, String nomuser, String comment, String valoracio, newCommentResponse callback) {
        //apliquem el patró singleton

        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        //Agafem l'id de la localització per crear el comment
        String id = l.getValue().getelembyname(nomlocalitzacio).getId();
        ComentariRepository.getInstance().newComment(id, nomuser, comment, valoracio, callback);
    }

}