package com.example.bcnet_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ViewLocalitzacio extends AppCompatActivity {
    private static final String TAG = "ViewLocalitzacio";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_localitzacio);
        Log.d(TAG, "onCreate: started. ");

        getIncomingIntent();
    }

    private void getIncomingIntent (){
        Log.d(TAG, "getIncomingIntent: check for incoming intents (Localitzacions)");
        if(getIntent().hasExtra("imatge")&& getIntent().hasExtra("nom_localitzacio")) {
            Log.d(TAG, "getIncomingIntent: ha trobat tots els extres");

            String imageUrl = getIntent().getStringExtra("imatge");
            String nom_localitzacio = getIntent().getStringExtra(("nom_localitzacio"));

            setImage(imageUrl, nom_localitzacio);
        }
    }
    private void setImage (String imageUrl, String nom_localitzacio){
        Log.d(TAG, "setImage: setting nom i imatge als widgets");

        TextView name = findViewById(R.id.Nom_Localitzacio);
        name.setText(nom_localitzacio);

        ImageView imatge = findViewById(R.id.imatge);
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background); //aixo possiblement s'haura de canviar
        Glide.with(this)
                .setDefaultRequestOptions(defaultOptions)
                .load(imageUrl)
                .into(imatge);
    }
}
