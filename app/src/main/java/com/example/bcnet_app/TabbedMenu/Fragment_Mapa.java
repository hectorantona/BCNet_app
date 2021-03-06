package com.example.bcnet_app.TabbedMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.bcnet_app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Mapa extends Fragment implements OnMapReadyCallback {
    private Float latitud;
    private Float longitud;
    private String nomlocalitzacio;
    private GoogleMap mapa;

    public Fragment_Mapa(String nomlocalitzacio, Float latitud, Float longitud) {
        this.latitud = latitud;
        this.longitud =  longitud;
        this.nomlocalitzacio = nomlocalitzacio;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__mapa, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }


    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        LatLng ubicacio = new LatLng(latitud,longitud);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(ubicacio.latitude, ubicacio.longitude))
                .title(nomlocalitzacio));

        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacio, 13));
    }
}