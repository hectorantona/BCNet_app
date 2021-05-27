package com.example.bcnet_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.viewmodels.UserViewModel;

public class PerfilActivity extends AppCompatActivity {

    private Button BtnModifyPswd;
    private Button BtnModifyEmail;
    private Button BtnModifyProfilePicture;
    private ImageView userimg;
    private Button NewLocalBtn;
    private UserViewModel viewModel;

    private SharedPreferences mPreferences;
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";
    private final String EMAIL_KEY = "email";
    private final String USERIMAGE_KEY = "userimage";

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
                Intent startIntent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(startIntent);
                break;
            case R.id.mapa:
                Intent intent = new Intent(getApplicationContext(), SearchMapaActivity.class);
                startActivity(intent);
                break;
            case R.id.perfil:
                Intent perfilintent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(perfilintent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        NewLocalBtn = (Button)findViewById(R.id.GotoCreateLocalBtn);
        mPreferences = getSharedPreferences("User", 0);

        getIncomingIntent();

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.init();

        mPreferences = getSharedPreferences("User", 0);

        BtnModifyProfilePicture = (Button)findViewById(R.id.BtnChangeProfilePicture);
        BtnModifyProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FormNewProfilePictureActivity.class);
                startActivity(startIntent);
            }
        });

        BtnModifyPswd = (Button)findViewById(R.id.BtnChangePwd);
        BtnModifyPswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FormNewPswd.class);
                startActivity(startIntent);
            }
        });

        BtnModifyEmail = (Button)findViewById(R.id.BtnChangeEmail);
        BtnModifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FormNewEmail.class);
                startActivity(startIntent);
            }
        });

        NewLocalBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent startIntent = new Intent(getApplicationContext(), NewLocalActivity.class);
                   startActivity(startIntent);
               }
           }
        );
    }

    private void getIncomingIntent() {
        TextView username = findViewById(R.id.Nom_Usuari);
        mPreferences = getSharedPreferences("User", 0);
        username.setText(mPreferences.getString("username", null));

        TextView email = findViewById(R.id.Email_Usuari);
        mPreferences = getSharedPreferences("User", 0);
        email.setText(mPreferences.getString("email", null));

        userimg = findViewById(R.id.imatge_perfil);
        mPreferences = getSharedPreferences("User", 0);
        String profileimgUrl = mPreferences.getString(USERIMAGE_KEY, null);
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background); //aixo possiblement s'haura de canviar
        Glide.with(this)
                .setDefaultRequestOptions(defaultOptions)
                .load(profileimgUrl)
                .into(userimg);


    }
}