package com.example.bcnet_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.Factory.MyViewModelFactory;
import com.example.bcnet_app.viewmodels.CommentViewModel;

public class FormValoracio extends AppCompatActivity {
    private static final String TAG = "FormValoracio";
    private CommentViewModel commentViewModel;
    private String nom_localitzacio;
    private Button commentButton;
    private EditText comentari;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_valoracio);
        Log.d(TAG, "onCreate: started. ");

        getIncomingIntent();

        commentViewModel = new ViewModelProvider(this, new MyViewModelFactory(nom_localitzacio)).get(CommentViewModel.class);
        commentViewModel.init();

        comentari = findViewById(R.id.editTextComentari);
        commentButton = findViewById(R.id.BtnComentar);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newcomment();
            }
        });
    }


    private void newcomment() {
        String comment = comentari.getText().toString();
        Log.d(TAG, "Nou comentari" + comment);
        //Canviar Adria per userlogged.name()
        commentViewModel.newComment(nom_localitzacio, "Adria", comment);
    }

    private void getIncomingIntent (){
        Log.d(TAG, "getIncomingIntent: check for incoming intents (Comment)");
        nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");

        TextView name = findViewById(R.id.Nom_Localitzacio);
        name.setText(nom_localitzacio);


    }
}