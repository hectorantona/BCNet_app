package com.example.bcnet_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.Factory.MyViewModelFactory;
import com.example.bcnet_app.adapter.CommentAdapter;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.viewmodels.CommentViewModel;

public class ViewLocalitzacio extends AppCompatActivity {
    private static final String TAG = "ViewLocalitzacio";

    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;
    private CommentViewModel commentViewModel;
    private String nom_localitzacio;


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Tornem a buscar els comentaris");
        commentViewModel.searchComments();
        /*commentViewModel.getComentaris().observe(this, new Observer<CommentResponse>() {
            @Override
            public void onChanged(CommentResponse comentaris) {
                if (comentaris != null) {
                    mAdapter.setResults(comentaris);
                }

            }
        });*/
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_localitzacio);


        initRecycleView();

        getIncomingIntent();

        commentViewModel = new ViewModelProvider(this, new MyViewModelFactory(nom_localitzacio)).get(CommentViewModel.class);
        commentViewModel.init();

        commentViewModel.searchComments();

        commentViewModel.getComentaris().observe(this, new Observer<CommentResponse>() {
            @Override
            public void onChanged(CommentResponse comentaris) {
                if (comentaris != null) {
                    mAdapter.setResults(comentaris);
                }

            }
        });

        Button BtnValorar = (Button)findViewById(R.id.BtnValorar);
        BtnValorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FormValoracio.class);
                //
                startIntent.putExtra("nom_localitzacio", getIntent().getStringExtra("nom_localitzacio"));
                startActivity(startIntent);
                onPause();
            }
        });
        Button BtnCovid = (Button)findViewById(R.id.BtnCovid);
        BtnCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FormCovid.class);
                //

                startIntent.putExtra("nom_localitzacio", getIntent().getStringExtra("nom_localitzacio"));
                startActivity(startIntent);
                onPause();
            }
        });

    }

    private void initRecycleView() {
        mAdapter = new CommentAdapter(this);
        mRecyclerView = findViewById(R.id.llista_comentari);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getIncomingIntent (){
        if(getIntent().hasExtra("imatge")&& getIntent().hasExtra("nom_localitzacio")) {

            String imageUrl = getIntent().getStringExtra("imatge");
            nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");
            String content = getIntent().getStringExtra("content");
            Float puntuacio_global = Float.parseFloat(getIntent().getStringExtra("puntuacio_global"));
            String puntuacio_Covid = getIntent().getStringExtra("puntuacioCovid");

            setImage(imageUrl, nom_localitzacio, content);

            RatingBar puntuacioGlobal = findViewById(R.id.puntuacioGlobal);
            puntuacioGlobal.setRating(puntuacio_global);

            TextView puntuacioCovid = findViewById(R.id.puntuacioCovid);
            puntuacioCovid.setText(puntuacio_Covid);
        }
    }
    private void setImage (String imageUrl, String nom_localitzacio, String content){

        TextView name = findViewById(R.id.Nom_Localitzacio);
        name.setText(nom_localitzacio);

        TextView descripcio = findViewById(R.id.descripcio);
        descripcio.setText(content);

        ImageView imatge = findViewById(R.id.imatge);
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background); //aixo possiblement s'haura de canviar
        Glide.with(this)
                .setDefaultRequestOptions(defaultOptions)
                .load(imageUrl)
                .into(imatge);
    }
}
