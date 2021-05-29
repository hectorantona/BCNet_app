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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.TabbedMenu.FragmentAdapter;
import com.example.bcnet_app.response.InfoLocalitzacioResponse;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;
import com.google.android.material.tabs.TabLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;


public class ViewLocalitzacio extends AppCompatActivity {
    private static final String TAG = "ViewLocalitzacio";

    private String nom_localitzacio;
    private SharedPreferences mPreferences;
    private String loc_id;
    private float latitud;
    private float longitud;
    private TabLayout tabLayout;
    private ViewPager2 viewpager;
    private FragmentAdapter adapter;
    private RatingBar puntuacioGlobal;



    private MainActivity2ViewModel viewModel;

    private Button BtnValorar;
    private LikeButton heartBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_localitzacio);

        getIncomingIntent();

        viewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        viewModel.init();
        viewModel.initPref();

        //TabLayout
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewpager = findViewById(R.id.viewpager);
        viewpager.setUserInputEnabled(false);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), nom_localitzacio, latitud, longitud);
        viewpager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Comentaris"));
        tabLayout.addTab(tabLayout.newTab().setText("Mapa"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 viewpager.setCurrentItem(tab.getPosition(), false);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
/*
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(0));
            }
        });*/

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
                mPreferences = getSharedPreferences("User", 0);
                String nomUsuari = mPreferences.getString("username", null);
                viewModel.setLocalitzacioPref(nomUsuari, loc_id);
                //Afegir a preferits
                Log.d(TAG, "LIKE DE: " + nomUsuari); //PROVES FUNCIONAMENT sharedPreferences
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mPreferences = getSharedPreferences("User", 0);
                String nomUsuari = mPreferences.getString("username", null);
                viewModel.setLocalitzacioUnpref(nomUsuari, loc_id);
            }
        });

    }

    public void onResume() {
        super.onResume();
        //Log.d(TAG, "Tornem a buscar els comentaris");
        viewModel.searchLocalitzacio(loc_id, new InfoLocalitzacioResponse() {
            @Override
            public void infolocalitzacio (Float puntuacio) {
                puntuacioGlobal.setRating(puntuacio);
            }
        });

    }


    private void getIncomingIntent (){
        if(getIntent().hasExtra("imatge")&& getIntent().hasExtra("nom_localitzacio")) {

            String imageUrl = getIntent().getStringExtra("imatge");
            nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");
            loc_id =getIntent().getStringExtra("id");
            String content = getIntent().getStringExtra("content");
            Float puntuacio_global = getIntent().getFloatExtra("puntuacio_global", 0);
            String puntuacio_Covid = getIntent().getStringExtra("puntuacioCovid");
            latitud = getIntent().getFloatExtra("latitud", 0);
            longitud = getIntent().getFloatExtra("longitud", 0);
            String horari_loc = getIntent().getStringExtra("horari");
            String imageSemaforUrl = getIntent().getStringExtra("semafor");




            setImage(imageUrl, nom_localitzacio, content);

            puntuacioGlobal = findViewById(R.id.puntuacioGlobal);
            puntuacioGlobal.setRating(puntuacio_global);

            TextView horari = findViewById(R.id.horari);
            horari.setText(horari_loc);

            ImageView imatge_semafor = findViewById(R.id.semaforCovid);
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.ic_launcher_background); //aixo possiblement s'haura de canviar
            Glide.with(this)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(imageSemaforUrl)
                    .into(imatge_semafor);


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
