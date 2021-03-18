package com.example.bcnet_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity{



    EditText user, password;
    Button LoginBtn, SignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = findViewById(R.id.password);
        user = findViewById(R.id.user);
        SignupBtn = (Button)findViewById(R.id.SignupBtn);
        LoginBtn = (Button)findViewById(R.id.LoginBtn);

        //click login
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate inputs
                String useri = String.valueOf(user.getText());
                String passwordi = String.valueOf(password.getText());

                Intent startIntent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(startIntent);
            }
        });

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SignUpActivity.class);
                //
                startActivity(startIntent);
            }
        });
    }
}