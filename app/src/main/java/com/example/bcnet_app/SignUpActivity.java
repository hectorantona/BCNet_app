package com.example.bcnet_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText pass;
    private EditText reppass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.editText_username);
        email = (EditText) findViewById(R.id.editText_mail);
        pass = (EditText) findViewById(R.id.editText_pswd1);
        reppass = (EditText) findViewById(R.id.editText_pswd2);

        Button BtnSignUpContinue = (Button)findViewById(R.id.BtnContinue);
        BtnSignUpContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity2.class);
                //attemptloggin();
                startActivity(startIntent);
            }
        });
    }

    private boolean isUserValid(String userID) {
       // return userID.length() == 10;
        return true;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
/*
    private void attemptLogin() {
       /* if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mFloatLabelUserId.setError(null);
        mFloatLabelPassword.setError(null);

        // Store values at the time of the login attempt.
        String userId = username.getText().toString();
        String password = pass.getText().toString();
        String reppassword = reppass.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
            focusView = mFloatLabelPassword;
            cancel = true;
        }*/
/*
        // Verificar si el ID tiene contenido.
        if (TextUtils.isEmpty(userId)) {
            mFloatLabelUserId.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelUserId;
            cancel = true;
        } else if (!isUserIdValid(userId)) {
            mFloatLabelUserId.setError(getString(R.string.error_invalid_user_id));
            focusView = mFloatLabelUserId;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(userId, password);
            mAuthTask.execute((Void) null);
        }
    }*/
}