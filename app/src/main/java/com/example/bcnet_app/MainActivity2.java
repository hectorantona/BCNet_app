package com.example.bcnet_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MAINPAGE";
    private SharedPreferences mPreferences;
    private final String USERNAME_KEY = "username";
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
                Toast.makeText(this, "Esta es la página principal", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.perfil:
                Intent startIntent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(startIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        Log.d(TAG, "USERNAME: " + mPreferences.getString("username", null)); //PROVES FUNCIONAMENT sharedPreferences

    }
}