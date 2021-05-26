package com.example.bcnet_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.response.InfoUserResponse;
import com.example.bcnet_app.response.allLocalitzacionsResponse;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;
import com.example.bcnet_app.viewmodels.UserViewModel;
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

    //MENÚ
    private final String USERNAME_KEY = "username";
    private final String EMAIL_KEY = "email";
    private final String USERIMAGE_KEY = "userimage";
    private final String PASSWORD_KEY = "password";
    private UserViewModel userViewModel;
    private SharedPreferences mPreferences;

    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mapa);
        mPreferences = getSharedPreferences("User", 0);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent homeintent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(homeintent);

            case R.id.perfil:
                userViewModel.infouser(mPreferences.getString("username", null), new InfoUserResponse() {
                    @Override
                    public void infouser(String Username, String email, String password, String profilepicture, Boolean message, String errormsg) {
                        if (message) {
                            SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                            sharedpreferenceseditor.putString(EMAIL_KEY, email);
                            sharedpreferenceseditor.putString(PASSWORD_KEY, password);
                            sharedpreferenceseditor.putString(USERIMAGE_KEY, profilepicture);
                            sharedpreferenceseditor.apply();
                            Intent startIntent = new Intent(getApplicationContext(), PerfilActivity.class);
                            startActivity(startIntent);
                        }
                    }
                });

            case R.id.mapa:
                Intent intent = new Intent(getApplicationContext(), SearchMapaActivity.class);
                startActivity(intent);

            case R.id.logout:
                SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                sharedpreferenceseditor.clear();
                //Intent finishIntent = new Intent(getApplicationContext(),SignInActivity.class);
                //startActivity(finishIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}