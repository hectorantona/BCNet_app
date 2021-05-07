package com.example.bcnet_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
    private RatingBar puntuacio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_valoracio);

        getIncomingIntent();

        commentViewModel = new ViewModelProvider(this, new MyViewModelFactory(nom_localitzacio)).get(CommentViewModel.class);
        commentViewModel.init();

        puntuacio = findViewById(R.id.puntuacioIndividual);
        comentari = findViewById(R.id.editTextComentari);
        commentButton = findViewById(R.id.BtnComentar);
        puntuacio.setNumStars(5);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newcomment();
                finish();
            }
        });
    }


    private void newcomment() {
        //PREGUNTAR AL PROFE COM FER QUE LA FUNCIO DE DEMANAR ERROR S'ESPERI A QUE RETRORIFT HAGI ACABAT
        String comment = comentari.getText().toString();
        //Canviar Adria per userlogged.name()
        Boolean error = commentViewModel.newComment(nom_localitzacio, "Maria", comment);
        //No s'ha pogut crear be. S'ha de fer perque l'user ho vegi
        if (!error)  {
           //Avisar de l'error
        }
    }

    private void puntuacio() {

    }

    private void getIncomingIntent (){

        nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");

        TextView name = findViewById(R.id.Nom_Localitzacio);
        name.setText(nom_localitzacio);


    }
}