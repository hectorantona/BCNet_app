package com.example.bcnet_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bcnet_app.Factory.CovidViewModelFactory;
import com.example.bcnet_app.Factory.ValoracioViewModelFactory;
import com.example.bcnet_app.response.newCovidCommentResponse;
import com.example.bcnet_app.viewmodels.CovidCommentsViewModel;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormCommentCovid extends AppCompatActivity {
    private static final String TAG = "FormValoracio ";
    private CovidCommentsViewModel covidCommentsViewModel;
    private MainActivity2ViewModel localitzacioViewModel;
    private String nom_localitzacio; //AFEGIR MES COSES
    private Button commentButton;
    private EditText comentari;
    private SharedPreferences mPreferences;
    private CheckBox check_gelHidroalcoholic;
    private CheckBox check_distanciaSeguretat;
    private CheckBox check_termometre;
    private CheckBox check_mascareta;
    private TextInputLayout mFloatLabelTextComm;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_covid);
        Log.d(TAG, "onCreate: started. ");

        getIncomingIntent();
        covidCommentsViewModel = new ViewModelProvider(this, new CovidViewModelFactory(nom_localitzacio)).get(CovidCommentsViewModel.class);
        covidCommentsViewModel.init();

        localitzacioViewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        localitzacioViewModel.init();

        comentari = findViewById(R.id.editTextComentari); //AFEGIR MES COSES
        commentButton = findViewById(R.id.BtnComentar);

        mFloatLabelTextComm = (TextInputLayout) findViewById(R.id.float_label_text_comment);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss" );


        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newcovidcomment();
                //afegeixpuntuaciocovid(); //FALTA FER MITJA DE SEMAFOR TOTAL
                //finish();
            }
        });
    }

    private void newcovidcomment() {
        String comment = comentari.getText().toString(); //AFEGIR COSES MASCARETA ETC
        Integer puntuacio = calcularpuntuacio();
        Log.d(TAG, "PUNTUACIO = " + puntuacio.toString());
        mPreferences = getSharedPreferences("User", 0);
        //String pref = this.getPreferenceManager().getSharedPreferencesName();
        String nomuser = mPreferences.getString("username", null);
        date = dateFormat.format(calendar.getTime());
        //amagarUI();
        covidCommentsViewModel.newComment(nom_localitzacio, nomuser, puntuacio.toString(), comment, new newCovidCommentResponse() {
            @Override
            public void updateCovidcomments(Boolean correcte) {
                if (!correcte) {
                    mFloatLabelTextComm.setError(getString(R.string.error_ja_te_comment));
                }
                else {
                    //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    finish();
                }
            }
        });
    }

    private Integer calcularpuntuacio() {
        check_gelHidroalcoholic = findViewById(R.id.checkBox_gelHidroalcoholic);
        check_distanciaSeguretat = findViewById(R.id.checkBox_distanciaSeguretat);
        check_termometre = findViewById(R.id.checkBox_termometre);
        check_mascareta = findViewById(R.id.checkBox_mascareta);
        Boolean gelHidroalcoholic = check_gelHidroalcoholic.isChecked();
        Boolean distanciaSeguretat = check_distanciaSeguretat.isChecked();
        Boolean termometre = check_termometre.isChecked();
        Boolean mascareta = check_mascareta.isChecked();
        Integer aux = 0;
        if(gelHidroalcoholic) aux = aux + 1;
        if(distanciaSeguretat) aux = aux + 1;
        if(termometre) aux = aux + 1;
        if(mascareta) aux = aux + 1;
        Integer puntuacio = (aux*10)/4; //Per donar el valor que necessiti back
        if (puntuacio == 0) puntuacio = 1;
        return puntuacio;
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: check for incoming intents (Covid)");
        nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");

        TextView name = findViewById(R.id.Nom_Localitzacio);
        name.setText(nom_localitzacio);
    }
}