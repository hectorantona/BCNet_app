package com.example.bcnet_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.TabbedMenu.FragmentAdapter;
import com.example.bcnet_app.response.InfoLocalitzacioResponse;
import com.example.bcnet_app.response.InfoUserResponse;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;
import com.example.bcnet_app.viewmodels.UserViewModel;
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
    //MENÚ
    private final String USERNAME_KEY = "username";
    private final String EMAIL_KEY = "email";
    private final String USERIMAGE_KEY = "userimage";
    private final String PASSWORD_KEY = "password";
    private UserViewModel userViewModel;

    private MainActivity2ViewModel viewModel;

    private Button BtnValorar;
    private LikeButton heartBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_localitzacio);

        getIncomingIntent();

        mPreferences = getSharedPreferences("User", 0);
        viewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);
        viewModel.init();
        viewModel.initPref();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();

        //TabLayout
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewpager = findViewById(R.id.viewpager);
        viewpager.setUserInputEnabled(false);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), nom_localitzacio, latitud, longitud);
        viewpager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Comentari)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Mapa)));
        tabLayout.addTab(tabLayout.newTab().setText("Covid"));


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
                Intent startIntent = new Intent(getApplicationContext(), FormCommentCovid.class);
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
                Log.d(TAG, "LIKE DE: " + nomUsuari);
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
                Log.d(TAG, "la puntuacio és" + puntuacio);
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

            setImage(imageUrl, nom_localitzacio, content);

            puntuacioGlobal = findViewById(R.id.puntuacioGlobal);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent homeintent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(homeintent);

            case R.id.perfil:
                userViewModel.infouser(mPreferences.getString("username", null), new InfoUserResponse() {
                    @Override
                    public void infouser(String Username, String email, String password, String profilepicture, Boolean message, String errormsg) {
                        if (message) {
                            SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                            sharedpreferenceseditor.putString(EMAIL_KEY, email);
                            sharedpreferenceseditor.putString(PASSWORD_KEY, password);
                            sharedpreferenceseditor.putString(USERIMAGE_KEY, profilepicture);
                            sharedpreferenceseditor.apply();
                            Intent startIntent = new Intent(getApplicationContext(), PerfilActivity.class);
                            startActivity(startIntent);
                        }
                    }
                });

            case R.id.mapa:
                Intent intent = new Intent(getApplicationContext(), SearchMapaActivity.class);
                startActivity(intent);

            case R.id.logout:
                SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                sharedpreferenceseditor.clear();
                //Intent finishIntent = new Intent(getApplicationContext(),SignInActivity.class);
                //startActivity(finishIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
