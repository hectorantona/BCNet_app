package com.example.bcnet_app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bcnet_app.R;
import com.example.bcnet_app.adapter.RecyclerViewAdapter;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;

public class LocalitzacioSearchFrag extends Fragment {
    private MainActivity2ViewModel viewModel;
    private RecyclerViewAdapter adapter;

    private EditText nameEditText;
    private Button searchButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new RecyclerViewAdapter(getActivity());

        viewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        viewModel.init();
        //fero tmb per el response per ara no esta
        viewModel.getLocalitzacions().observe(this, new Observer<LocalitzacionsSearch>() {
            @Override
            public void onChanged(LocalitzacionsSearch l) {
                Log.d("Fragment", "Que esta passant" + l);
                if (l != null) {
                    Log.d("Fragment", "no null" + l.getnumelements());
                    adapter.setResults(l);
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_localitzacio_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        nameEditText = view.findViewById(R.id.fragment_localitzacio_name);
        searchButton = view.findViewById(R.id.fragment_localitzacio_search);

        viewModel.searchAllLocalitzacions();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
        return view;
    }

    public void performSearch() {
        String name = nameEditText.getText().toString();

        viewModel.searchLocalitzacions(name);
    }
}
