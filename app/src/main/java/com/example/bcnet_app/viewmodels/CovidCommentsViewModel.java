package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.adapter.CommentAdapter;
import com.example.bcnet_app.adapter.CovidCommentAdapter;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.DadesCovidResponse;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.ComentariRepository;
import com.example.bcnet_app.repositories.DadesCovidRepository;
import com.example.bcnet_app.repositories.LocalitzacioRespository;
import com.example.bcnet_app.response.newCommentResponse;
import com.example.bcnet_app.response.newCovidCommentResponse;

public class CovidCommentsViewModel extends ViewModel {

    private static final String TAG = "CovidCommentViewModel";
    private LiveData<DadesCovidResponse> mComentari;
    private CovidCommentAdapter mAdapter;
    private String nomlocalitzacio;
    private String idlocalitzacio;

    public CovidCommentsViewModel(String nomloca) {
        nomlocalitzacio = nomloca;
    }

    public void init () {
        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        idlocalitzacio = l.getValue().getelembyname(nomlocalitzacio).getId();

        mComentari = DadesCovidRepository.getInstance().getcovidcomments();
    }
    public LiveData<DadesCovidResponse> getComentaris () { return mComentari;}

    public void newComment (String nomlocalitzacio, String nomuser, String comment, String comentari, newCovidCommentResponse callback) {

        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        String id = l.getValue().getelembyname(nomlocalitzacio).getId();
        DadesCovidRepository.getInstance().newCovidComment(id, nomuser, comment, comentari, callback);
    }

    public void searchCovidComments() {
        DadesCovidRepository.getInstance().searchCovidComments(idlocalitzacio);

    }





}
