package com.example.bcnet_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.response.InfoUserResponse;
import com.example.bcnet_app.viewmodels.UserViewModel;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MAINPAGE";
    private SharedPreferences mPreferences;
    private final String USERNAME_KEY = "username";
    private final String EMAIL_KEY = "email";
    private final String USERIMAGE_KEY = "userimage";
    private final String PASSWORD_KEY = "password";
    private UserViewModel userViewModel;


    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";




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
                Intent startintent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(startintent);

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
                            Log.d(TAG, "EMAIL: " + mPreferences.getString("email", null)); //PROVES FUNCIONAMENT sharedPreferences
                            Intent startIntent = new Intent(getApplicationContext(), PerfilActivity.class);
                            startActivity(startIntent);
                        }
                    }
                });
                break;

            case R.id.mapa:
                Log.d(TAG, "nos vamos pal mapa");
                Intent intent = new Intent(getApplicationContext(), SearchMapaActivity.class);
                startActivity(intent);
                break;

            case R.id.logout:
                SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                sharedpreferenceseditor.clear();
                Intent finishIntent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(finishIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mPreferences = getSharedPreferences("User", 0);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();

    }
}