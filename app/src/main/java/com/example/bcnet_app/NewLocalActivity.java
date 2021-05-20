package com.example.bcnet_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
    private Spinner categoria_spiner;
    private CustomScrollView scroll;
    private Button crearloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_local);

        scroll= (CustomScrollView) findViewById(R.id.scroll);
        nomloc = findViewById(R.id.local_name);
        crearloc = findViewById(R.id.CreateLocalBtn);

        //Set spiner
        categoria_spiner = findViewById(R.id.categoria_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria_spiner.setAdapter(adapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        crearloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newlocalitzacio();
                //finish();
            }
        });
    }

    private void newlocalitzacio() {
        //Agafem els parametres introduits anteriorment i fem la crida a back
        //Faltaria veure que tots els camps estan omplerts i que sigui correcte...
        String categoria = categoria_spiner.getSelectedItem().toString();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        //Es fa autozoom a les co
        LatLng origen = new LatLng(41.3890927,2.1575136);
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(origen, 13));

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