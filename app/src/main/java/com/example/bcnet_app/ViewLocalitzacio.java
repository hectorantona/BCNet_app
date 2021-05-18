package com.example.bcnet_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.Factory.MyViewModelFactory;
import com.example.bcnet_app.adapter.CommentAdapter;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.LocalitzacionsSearch;
import com.example.bcnet_app.repositories.LocalitzacioRespository;
import com.example.bcnet_app.viewmodels.CommentViewModel;
import com.like.LikeButton;
import com.like.OnLikeListener;

public class ViewLocalitzacio extends AppCompatActivity {
    private static final String TAG = "ViewLocalitzacio";

    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;
    private CommentViewModel commentViewModel;
    private String nom_localitzacio;
    private String nomuser;
    private SharedPreferences mPreferences;

    private Button BtnValorar;
    private LikeButton heartBtn;

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Tornem a buscar els comentaris");
        commentViewModel.searchComments();

    }

    public void updateComments() {
        onResume();
        Log.d(TAG, "fem update dels comments ");
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

        getIncomingIntent();

        initRecycleView();

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

        BtnValorar = (Button)findViewById(R.id.BtnValorar);
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


        heartBtn = (LikeButton)findViewById(R.id.heart_button);
        heartBtn.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                //Afegir a preferits
                Log.d(TAG, "LIKE DE: " + mPreferences.getString("username", null)); //PROVES FUNCIONAMENT sharedPreferences
            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });

    }

    private void initRecycleView() {
        //Si ho podem fer amb l'id del comment millor que amb aixo
        LiveData<LocalitzacionsSearch> l = LocalitzacioRespository.getInstance().getlocalitzacions();
        //Agafem l'id de la localització per crear el comment
        Log.d(TAG, "nom localitzacio: " + nom_localitzacio);
        String idlocalitzacio = l.getValue().getelembyname(nom_localitzacio).getId();

        mPreferences = getSharedPreferences("User", 0);
        nomuser = mPreferences.getString("username", null);

        mAdapter = new CommentAdapter(this, idlocalitzacio, nomuser);
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
