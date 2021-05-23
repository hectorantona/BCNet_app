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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.TabbedMenu.FragmentAdapter;
import com.example.bcnet_app.adapter.CommentAdapter;
import com.example.bcnet_app.viewmodels.CommentViewModel;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;
import com.google.android.material.tabs.TabLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;


public class ViewLocalitzacio extends AppCompatActivity {
    private static final String TAG = "ViewLocalitzacio";

    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;
    private CommentViewModel commentViewModel;
    private String nom_localitzacio;
    private SharedPreferences mPreferences;
    private String loc_id;
    private String latitud;
    private String longitud;
    private TabLayout tabLayout;
    private ViewPager2 viewpager;
    private FragmentAdapter adapter;

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
        viewpager = findViewById(R.id.viewpager);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), nom_localitzacio, latitud, longitud);
        viewpager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Comentaris"));
        tabLayout.addTab(tabLayout.newTab().setText("Mapa"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 viewpager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
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


    private void getIncomingIntent (){
        if(getIntent().hasExtra("imatge")&& getIntent().hasExtra("nom_localitzacio")) {

            String imageUrl = getIntent().getStringExtra("imatge");
            nom_localitzacio = getIntent().getStringExtra("nom_localitzacio");
            loc_id =getIntent().getStringExtra("id");
            String content = getIntent().getStringExtra("content");
            Float puntuacio_global = Float.parseFloat(getIntent().getStringExtra("puntuacio_global"));
            String puntuacio_Covid = getIntent().getStringExtra("puntuacioCovid");
            latitud = getIntent().getStringExtra("latitud");
            longitud = getIntent().getStringExtra("longitud");

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
