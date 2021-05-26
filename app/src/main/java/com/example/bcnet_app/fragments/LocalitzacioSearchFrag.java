package com.example.bcnet_app.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

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
import com.example.bcnet_app.response.allLocalitzacionsResponse;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;

public class LocalitzacioSearchFrag extends Fragment {
    private MainActivity2ViewModel viewModel;
    private RecyclerViewAdapter adapter;

    private RecyclerView recyclerView;
    private EditText nameEditText;
    private Spinner searchSpinner;
    private Button searchButton;

    private ProgressBar progessBar;
    private SharedPreferences mPreferences;
    private String nomuser;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new RecyclerViewAdapter(getActivity());

        viewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        viewModel.init();
        viewModel.initPref();


        //fero tmb per el response per ara no esta
        viewModel.getLocalitzacions().observe(this, new Observer<LocalitzacionsSearch>() {
            @Override
            public void onChanged(LocalitzacionsSearch l) {
                if (l != null) {
                    adapter.setResults(l);
                    showUI();
                }
            }
        });

    }

    private void amagarUI () {
        recyclerView.setVisibility(View.GONE);
        progessBar.setVisibility(View.VISIBLE);
    }

    private void showUI() {
        progessBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.searchAllLocalitzacions(new allLocalitzacionsResponse() {
            @Override
            public void allLocalitzacions(LocalitzacionsSearch l) {}
        });

        viewModel.getLocalitzacions().observe(this, new Observer<LocalitzacionsSearch>() {
            @Override
            public void onChanged(LocalitzacionsSearch l) {
                if (l != null) {
                    adapter.setResults(l);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_localitzacio_search, container, false);

        progessBar = view.findViewById(R.id.progress_bar);
        progessBar.setVisibility(View.GONE);


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        nameEditText = view.findViewById(R.id.fragment_localitzacio_name);

        searchSpinner = view.findViewById(R.id.search_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.search_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(adapter);

        searchButton = view.findViewById(R.id.fragment_localitzacio_search);

        viewModel.searchAllLocalitzacions(new allLocalitzacionsResponse() {
            @Override
            public void allLocalitzacions(LocalitzacionsSearch l) {}
        });

        mPreferences = this.getActivity().getSharedPreferences("User", 0);
        String nomUsuari = mPreferences.getString("username", null);
        viewModel.searchPrefLocalitzacions(nomUsuari);

        amagarUI();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opt = searchSpinner.getSelectedItem().toString();
                switch (opt) {
                    case "Nom":
                        performSearchByName();
                        break;

                    case "Categoria":
                        performSearchByCategory();
                        break;

                    case "Més puntuació":
                        orderByPuntuacioGlobal();
                        break;

                    case "Puntuació COVID":
                        break;

                    case "Adreça":
                        performSearchByAdreca();
                        break;

                    case "Preferits":
                        performSetPref();
                        break;

                    default:
                        break;
                }
            }
        });

        return view;
    }

    private void performSetPref() {
        /*
        viewModel.getLocalitzacions().observe(this, new Observer<LocalitzacionsSearch>() {
            @Override
            public void onChanged(LocalitzacionsSearch l) {
                if (l != null) {
                    adapter.setResults(l);
                }
            }
        });
         */

    }

    private void orderByPuntuacioGlobal() {
        LocalitzacionsSearch LS = viewModel.getLocalitzacions().getValue();
        LS.orderPuntuacioGlobal();
        adapter.setResults(LS);
    }

    private void performSearchByAdreca() {
        String adreca = nameEditText.getText().toString();
        LocalitzacionsSearch LS = new LocalitzacionsSearch(viewModel.getLocalitzacions().getValue().getLocalitzacions());
        LS.filterByAdreca(adreca);
        adapter.setResults(LS);
    }

    public void performSearchByName() {
        String name = nameEditText.getText().toString();
        LocalitzacionsSearch LS = new LocalitzacionsSearch(viewModel.getLocalitzacions().getValue().getLocalitzacions());
        LS.filterByName(name);
        adapter.setResults(LS);
    }

    private void performSearchByCategory() {
        String categ = nameEditText.getText().toString();
        LocalitzacionsSearch LS = new LocalitzacionsSearch(viewModel.getLocalitzacions().getValue().getLocalitzacions());
        LS.filterByCategory(categ);
        adapter.setResults(LS);
    }
}
