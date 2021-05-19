package com.example.bcnet_app.TabbedMenu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bcnet_app.Factory.MyViewModelFactory;
import com.example.bcnet_app.R;
import com.example.bcnet_app.adapter.CommentAdapter;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.LocalitzacioRespository;
import com.example.bcnet_app.viewmodels.CommentViewModel;

public class ComentarisFragment extends Fragment {
    private static final String TAG ="Comenaris Fragment";
    private RecyclerView mRecyclerView;
    private SharedPreferences mPreferences;
    private CommentViewModel commentViewModel;
    private CommentAdapter adapter;
    private String nom_localitzacio;

    public ComentarisFragment(String nomlocalitzacio) {
        this.nom_localitzacio = nomlocalitzacio;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        commentViewModel = new ViewModelProvider(this, new MyViewModelFactory(nom_localitzacio)).get(CommentViewModel.class);
        commentViewModel.init();

        commentViewModel.searchComments();

        commentViewModel.getComentaris().observe(this, new Observer<CommentResponse>() {
            @Override
            public void onChanged(CommentResponse comentaris) {
                if (comentaris != null) {
                    adapter.setResults(comentaris);
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comentaris, container, false);

        //Si ho podem fer amb l'id del comment millor que amb aixo
        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        //Agafem l'id de la localització per crear el comment
        Log.d(TAG, "nom localitzacio: " + nom_localitzacio);
        String idlocalitzacio = l.getValue().getelembyname(nom_localitzacio).getId();

        mPreferences = this.getActivity().getSharedPreferences("User", 0);
        String nomuser = mPreferences.getString("username", null);

        adapter = new CommentAdapter(getContext(), idlocalitzacio, nomuser);
        mRecyclerView = view.findViewById(R.id.llista_comentari);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.d(TAG, "Tornem a buscar els comentaris");
        commentViewModel.searchComments();

    }
}