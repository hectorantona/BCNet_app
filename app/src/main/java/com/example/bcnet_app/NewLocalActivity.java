package com.example.bcnet_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.response.NewLocalitzacioResponse;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputLayout;

public class NewLocalActivity extends AppCompatActivity implements OnMapReadyCallback  {

    private GoogleMap mapa;
    private MainActivity2ViewModel viewModel;

    private EditText nomloc;
    private EditText descripcio;
    private EditText web;
    private EditText img;

    private TextInputLayout mFloatLabelnomloc;
    private TextInputLayout mFloatLabeldescripcio;
    private TextInputLayout mFloatLabelweb;
    private TextInputLayout mFloatLabelimg;
    private TextInputLayout mFloatLabelmapa;
    private TextInputLayout mFloatLabelerror;

    private Spinner categoria_spiner;
    private CustomScrollView scroll;
    private Button crearloc;
    private LatLng coordenades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_local);

        viewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        viewModel.init();

        scroll= (CustomScrollView) findViewById(R.id.scroll);

        nomloc = findViewById(R.id.local_name);
        descripcio = findViewById(R.id.descripcio);
        web = findViewById(R.id.link);
        nomloc = findViewById(R.id.local_name);
        img = findViewById(R.id.img);

        mFloatLabelnomloc = findViewById(R.id.float_label_local_name);
        mFloatLabeldescripcio = findViewById(R.id.float_label_descripcio);
        mFloatLabelweb = findViewById(R.id.float_label_link);
        mFloatLabelimg = findViewById(R.id.float_label_img);
        mFloatLabelmapa = findViewById(R.id.float_label_mapa);
        mFloatLabelerror = findViewById(R.id.float_error);


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
        if (comprovarcamps()) {
            String nom_loc =  nomloc.getText().toString();
            String categoria = categoria_spiner.getSelectedItem().toString();
            String descripcio_text = descripcio.getText().toString();
            String web_text = web.getText().toString();
            String img_text = img.getText().toString();
            String latitud = String.valueOf(coordenades.latitude);
            String longitud = String.valueOf(coordenades.longitude);

            viewModel.newLocalitzacio(nom_loc, "bcn", "2", descripcio_text, web_text, img_text, "11", categoria, latitud, longitud, new NewLocalitzacioResponse() {
                @Override
                public void newlocalitzacio(Boolean correcte) {
                    //No es pot fer el comentari
                    if (!correcte) {
                        mFloatLabelerror.setError(getString(R.string.error_existeix_local));
                    }
                    //El comentari s'ha pogut fer
                    else {
                        finish();
                    }
                }
            });
        }

    }

    private Boolean comprovarcamps () {
        View focusview = null;
        Boolean correcte = true;

        if (nomloc.getText().toString().equals("")) {
            correcte = false;
            mFloatLabelnomloc.setError(getString(R.string.error_field_required));
            focusview = mFloatLabelnomloc;
        }
        if (descripcio.getText().toString().equals("")) {
            correcte = false;
            mFloatLabeldescripcio.setError(getString(R.string.error_field_required));
            focusview = mFloatLabeldescripcio;
        }
        if (web.getText().toString().equals("")) {
            correcte = false;
            mFloatLabelweb.setError(getString(R.string.error_field_required));
            focusview = mFloatLabelweb;
        }
        if (img.getText().toString().equals("")) {
            correcte = false;
            mFloatLabelimg.setError(getString(R.string.error_field_required));
            focusview = mFloatLabelimg;
        }
        if (coordenades==null) {
            correcte = false;
            mFloatLabelmapa.setError(getString(R.string.error_field_required));
            focusview = mFloatLabelmapa;
        }
        if (!correcte) focusview.requestFocus();
        return correcte;
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
                coordenades = latLng;
            }
        });

    }
}