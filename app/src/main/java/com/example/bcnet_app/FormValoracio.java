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
import com.example.bcnet_app.repositories.newCommentResponse;
import com.example.bcnet_app.viewmodels.CommentViewModel;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;

public class FormValoracio extends AppCompatActivity {
    private static final String TAG = "FormValoracio";
    private CommentViewModel commentViewModel;
    private MainActivity2ViewModel localitzacioViewModel;
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

        localitzacioViewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        localitzacioViewModel.init();

        puntuacio = findViewById(R.id.puntuacioIndividual);
        comentari = findViewById(R.id.editTextComentari);
        commentButton = findViewById(R.id.BtnComentar);
        puntuacio.setNumStars(5);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newcomment();
                afegeixpuntuacio();
                //finish();
            }
        });
    }


    private void newcomment() {
        //PREGUNTAR AL PROFE COM FER QUE LA FUNCIO DE DEMANAR ERROR S'ESPERI A QUE RETRORIFT HAGI ACABAT
        String comment = comentari.getText().toString();
        //Canviar Adria per userlogged.name()
        amagarUI();
        commentViewModel.newComment(nom_localitzacio, "Maria", comment, new newCommentResponse() {
            @Override
            public void updatecomments(Boolean error) {
                //No es pot fer el comentari
                if (!error) {

                }
                //El comentari s'ha pogut fer
                else {
                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    finish();
                }
            }
        });
    }

    private void amagarUI () {
        comentari.setVisibility(View.GONE);
        findViewById(R.id.Nom_Localitzacio).setVisibility(View.GONE);
        puntuacio.setVisibility(View.GONE);
        commentButton.setVisibility(View.GONE);
        comentari.setVisibility(View.GONE);
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
    }

    private void afegeixpuntuacio() {
        Integer punts = puntuacio.getNumStars();
        localitzacioViewModel.afegeixpuntuacio (nom_localitzacio, "Maria", punts.toString());

    }

    private void getIncomingIntent (){

        nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");

        TextView name = findViewById(R.id.Nom_Localitzacio);
        name.setText(nom_localitzacio);


    }
}