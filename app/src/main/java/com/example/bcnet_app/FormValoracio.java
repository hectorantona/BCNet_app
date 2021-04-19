package com.example.bcnet_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FormValoracio extends AppCompatActivity {
    private static final String TAG = "FormValoracio";
    private String nom_localitzacio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_valoracio);
        Log.d(TAG, "onCreate: started. ");

        getIncomingIntent();
    }
    private void getIncomingIntent (){
        Log.d(TAG, "getIncomingIntent: check for incoming intents (Comment)");
        String nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");

        TextView name = findViewById(R.id.Nom_Localitzacio);
        name.setText(nom_localitzacio);


    }
}