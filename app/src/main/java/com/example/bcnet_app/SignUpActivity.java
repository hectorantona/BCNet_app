package com.example.bcnet_app;

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

import com.example.bcnet_app.response.SignUpResponse;
import com.example.bcnet_app.viewmodels.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SIGNUP ACTIVITY";
    private EditText username;
    private EditText email;
    private EditText pass;
    private EditText reppass;

    private Button BtnSignUp;

    private TextInputLayout mFloatLabelNewUser;
    private TextInputLayout mFloatLabelNewEmail;
    private TextInputLayout mFloatLabelNewPassword;
    private TextInputLayout mFloatLabelRepPassword;

    private UserViewModel userViewModel;

    private SharedPreferences mPreferences;
    private final String USERNAME_KEY = "username";
    private final String PASSWORD_KEY = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.new_user);
        email = (EditText) findViewById(R.id.new_email);
        pass = (EditText) findViewById(R.id.new_password);
        reppass = (EditText) findViewById(R.id.rep_password);

        mFloatLabelNewUser = (TextInputLayout) findViewById(R.id.float_label_new_user);
        mFloatLabelNewEmail = (TextInputLayout) findViewById(R.id.float_label_new_email);
        mFloatLabelNewPassword = (TextInputLayout) findViewById(R.id.float_label_new_password);
        mFloatLabelRepPassword = (TextInputLayout) findViewById(R.id.float_label_rep_password);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();

        mPreferences = getApplicationContext().getSharedPreferences("User", 0);

        BtnSignUp = (Button)findViewById(R.id.SignUpBtn);
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptCreateAccount();
            }
        });
    }

    private boolean isUserValid(String userID) { return userID.length() > 0; }

    private boolean isEmailValid(String email) { return email.length() > 0; }

    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    private boolean doPasswordMatch(String pass, String rep_pass) { return pass.equals(rep_pass); }

    private void attemptCreateAccount() {

        // Reset errors.
        mFloatLabelNewUser.setError(null);
        mFloatLabelNewEmail.setError(null);
        mFloatLabelNewPassword.setError(null);
        mFloatLabelRepPassword.setError(null);

        // Store values at the time of the login attempt.
        String useri = username.getText().toString();
        String emaili = email.getText().toString();
        String passi = pass.getText().toString();
        String reppassi = reppass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Verificar si el ID tiene contenido.
        if (TextUtils.isEmpty(useri)) {
            mFloatLabelNewUser.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelNewUser;
            cancel = true;
        } else if (!isUserValid(useri)) {
            mFloatLabelNewUser.setError(getString(R.string.error_invalid_user));
            focusView = mFloatLabelNewUser;
            cancel = true;
        }

        // Verificar si el ID tiene contenido.
        if (TextUtils.isEmpty(emaili)) {
            mFloatLabelNewEmail.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelNewEmail;
            cancel = true;
        } else if (!isEmailValid(emaili)) {
            mFloatLabelNewEmail.setError(getString(R.string.error_invalid_email));
            focusView = mFloatLabelNewEmail;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(passi)) {
            mFloatLabelNewPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelNewPassword;
            cancel = true;
        } else if (!isPasswordValid(passi)) {
            mFloatLabelNewPassword.setError(getString(R.string.invalid_password));
            focusView = mFloatLabelNewPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(reppassi)) {
            mFloatLabelRepPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelRepPassword;
            cancel = true;
        } else if (!doPasswordMatch(passi, reppassi)) {
            mFloatLabelRepPassword.setError(getString(R.string.unmatched_passwords));
            focusView = mFloatLabelRepPassword;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //Login
            //Create user : loginViewModel.login(useri, emaili, passwordi);
            userViewModel.signup(emaili, useri, passi, new SignUpResponse() {

                @Override
                public void signup(String Username, Boolean message, String errormsg) {
                    if (message) {
                        SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                        sharedpreferenceseditor.putString(USERNAME_KEY, useri);
                        sharedpreferenceseditor.putString(PASSWORD_KEY, passi);
                        sharedpreferenceseditor.apply();
                        Log.d(TAG, "USERNAME: " + mPreferences.getString("username", null)); //PROVES FUNCIONAMENT sharedPreferences
                        Intent startIntent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(startIntent);
                    } else {
                        mFloatLabelNewUser.setError(getString(R.string.error_user_already_exists));

                    }
                }
            });
        }
    }
}