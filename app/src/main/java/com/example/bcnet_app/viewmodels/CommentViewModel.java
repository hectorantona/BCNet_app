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

    private static final String TAG = "CommentViewModel";
    private LiveData<CommentResponse> mComentari;
    private CommentAdapter mAdapter;
    private String nomlocalitzacio;
    private String idlocalitzacio;

    public CommentViewModel(String nomloca) {
        nomlocalitzacio = nomloca;
    }

    public void init () {

        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        idlocalitzacio = l.getValue().getelembyname(nomlocalitzacio).getId();

        mComentari = ComentariRepository.getInstance().getcomments();
    }

    public LiveData<CommentResponse> getComentaris () { return mComentari;}

    public void searchComments () {
        ComentariRepository.getInstance().searchComments(idlocalitzacio);

    }

    public void newComment (String nomlocalitzacio, String nomuser, String comment, String valoracio, newCommentResponse callback) {

        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        String id = l.getValue().getelembyname(nomlocalitzacio).getId();
        ComentariRepository.getInstance().newComment(id, nomuser, comment, valoracio, callback);
    }

}