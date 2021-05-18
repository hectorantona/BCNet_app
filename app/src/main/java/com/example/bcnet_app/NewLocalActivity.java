package com.example.bcnet_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NewLocalActivity extends AppCompatActivity implements OnMapReadyCallback  {

    private GoogleMap mapa;
    private EditText nomloc;
    private String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_local);

        nomloc = findViewById(R.id.local_name);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.categoria_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.museu:
                    categoria = "museu";
                return true;

            case R.id.restaurant:
                    categoria = "restaurant";
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        //Es fa autozoom a les co
        LatLng origen = new LatLng(41.3890927,2.1575136);
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(origen, 20));

        mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions().draggable(true);
                //Set marker position
                markerOptions.position(latLng);
                //Set laltitude and longitude
                markerOptions.title(nomloc.getText().toString());
                //Clear the previous click
                mapa.clear();
                //zoom the marker
                mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                //add marker on map
                mapa.addMarker(markerOptions);
            }
        });

    }
}