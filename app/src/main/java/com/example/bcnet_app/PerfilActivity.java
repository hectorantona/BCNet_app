package com.example.bcnet_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    private Button BtnModifyPswd;
    private Button BtnModifyEmail;
    private ImageView userimg;
    private Button NewLocalBtn;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";

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

            case R.id.perfil:
                Toast.makeText(this, "Este es tu perfil", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        NewLocalBtn = (Button)findViewById(R.id.GotoCreateLocalBtn);
        userimg = findViewById(R.id.imatge_perfil);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        getIncomingIntent();

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
        //Fer aixó o un get de bd amb tota la info i així ja tenim la foto
        TextView username = findViewById(R.id.Nom_Usuari);
        username.setText(mPreferences.getString("username", null));

        TextView email = findViewById(R.id.Email_Usuari);
        email.setText(mPreferences.getString("email", null));
    }
}