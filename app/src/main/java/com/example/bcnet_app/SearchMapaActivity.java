package com.example.bcnet_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.response.allLocalitzacionsResponse;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchMapaActivity extends AppCompatActivity implements OnMapReadyCallback {
    private LocalitzacionsSearch localitzacions;
    private MainActivity2ViewModel locviewModel;
    private GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mapa);
        locviewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        locviewModel.init();
        LiveData<LocalitzacionsSearch> l = locviewModel.getLocalitzacions();
        localitzacions = l.getValue();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onResume() {
        super.onResume();
        locviewModel.searchAllLocalitzacions(new allLocalitzacionsResponse() {
            @Override
            public void allLocalitzacions(LocalitzacionsSearch l) {
                localitzacions = l;
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        //Es fa autozoom a les co
        LatLng origen = new LatLng(41.3890927,2.1575136);
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(origen, 13));

        for (int i = 0; i < localitzacions.getnumelements(); ++i) {
            Localitzacio l = localitzacions.getelemi(i);
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(l.getLatitud(), l.getLongitud()))
                    .title(l.getName()));
        }

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Localitzacio l =localitzacions.getelembyname(marker.getTitle());
                viewlocalitzacio(l);
            }
        });
    }

    private void viewlocalitzacio (Localitzacio localitzacio) {
        Intent intent = new Intent(getApplicationContext(), ViewLocalitzacio.class);
        intent.putExtra("id", localitzacio.getId());
        intent.putExtra("imatge", localitzacio.getImageUrl());
        intent.putExtra("nom_localitzacio", localitzacio.getName());
        intent.putExtra("content", localitzacio.getContent());
        intent.putExtra("puntuacio_global", localitzacio.getPuntuacioGlobal());
        intent.putExtra("latitud", localitzacio.getLatitud());
        intent.putExtra("longitud", localitzacio.getLongitud());
        intent.putExtra("categoria", localitzacio.getCategory());
        intent.putExtra("puntuacioCovid", localitzacio.getPuntuacioCOVID());

        startActivity(intent);
    }
}