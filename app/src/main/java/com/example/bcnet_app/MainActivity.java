package com.example.bcnet_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.repositories.UserRepository;
import com.example.bcnet_app.viewmodels.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MAIN ACTIVITY";

    //private UserLoginTask mAuthTask = null;

    private EditText user;
    private EditText password;

    private Button LoginBtn;
    private Button SignupBtn;

    private TextInputLayout mFloatLabelUserId;
    private TextInputLayout mFloatLabelPassword;
    //private View mLoginFormView;

    private LoginViewModel loginViewModel;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);

        mFloatLabelUserId = (TextInputLayout) findViewById(R.id.float_label_user);
        mFloatLabelPassword = (TextInputLayout) findViewById(R.id.float_label_password);

        //mLoginFormView = findViewById(R.id.login_form);

        LoginBtn = (Button)findViewById(R.id.LoginBtn);
        SignupBtn = (Button)findViewById(R.id.SignupBtn);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init();

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);



        //click login
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attemptLogin();

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

    private boolean isUserIdValid (String u) {
        return u.length() > 0;
    }

    private boolean isPasswordValid(String p) {
        return p.length() >= 4;
    }

    private void attemptLogin() {

        // Reset errors.
        mFloatLabelUserId.setError(null);
        mFloatLabelPassword.setError(null);

        // Store values at the time of the login attempt.
        String useri = user.getText().toString();
        String passwordi = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(passwordi)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        } else if (!isPasswordValid(passwordi)) {
            mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
            focusView = mFloatLabelPassword;
            cancel = true;
        }

        // Verificar si el ID tiene contenido.
        if (TextUtils.isEmpty(useri)) {
            mFloatLabelUserId.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelUserId;
            cancel = true;
        } else if (!isUserIdValid(useri)) {
            mFloatLabelUserId.setError(getString(R.string.error_invalid_user));
            focusView = mFloatLabelUserId;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //Login
            loginViewModel.login(useri, passwordi);
            SharedPreferences.Editor sharedpreferences = mPreferences.edit();
            sharedpreferences.putString(USERNAME_KEY, useri);
            sharedpreferences.putString(PASSWORD_KEY, passwordi);
            sharedpreferences.apply();

            //SharedPreferences sharedpreferences = getSharedPreferences(UserRepository.MyPREFERENCES, Context.MODE_PRIVATE);//Commit de dades de l'user
            Log.d(TAG, "USERNAME: " + mPreferences.getString("username", null)); //PROVES FUNCIONAMENT sharedPreferences
            Intent startIntent = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(startIntent);
        }
    }
}