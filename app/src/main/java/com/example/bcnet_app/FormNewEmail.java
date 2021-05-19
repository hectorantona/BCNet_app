package com.example.bcnet_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bcnet_app.response.ChangeEmailResponse;
import com.example.bcnet_app.viewmodels.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class FormNewEmail extends AppCompatActivity {

    private static final String TAG = "FormNewEmail";
    private EditText pass;
    private EditText newemail;

    private Button BtnModify;

    private TextInputLayout mFloatLabelPassword;
    private TextInputLayout mFloatLabelNewEmail;

    private UserViewModel userViewModel;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    private final String EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_new_email);

        pass = (EditText) findViewById(R.id.password);
        newemail = (EditText) findViewById(R.id.new_email);

        mFloatLabelPassword = (TextInputLayout) findViewById(R.id.float_label_password);
        mFloatLabelNewEmail = (TextInputLayout) findViewById(R.id.float_label_new_email);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        BtnModify = (Button) findViewById(R.id.BtnModifyEmail);
        BtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptModifyEmail();
            }
        });
    }

    private void attemptModifyEmail() {
        mFloatLabelPassword.setError(null);
        mFloatLabelNewEmail.setError(null);

        String passi = pass.getText().toString();
        String newemaili = newemail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(passi)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(newemaili)) {
            mFloatLabelNewEmail.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelNewEmail;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            userViewModel.changeEmail(mPreferences.getString("username", null), passi, newemaili, new ChangeEmailResponse() {
                @Override
                public void changeEmail(String Username, String email, Boolean message, String errormsg) {
                    if (message) {
                        SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                        sharedpreferenceseditor.putString(EMAIL_KEY, newemaili);
                        sharedpreferenceseditor.apply();
                        Log.d(TAG, "NEW EMAIL: " + mPreferences.getString("email", null)); //PROVES FUNCIONAMENT sharedPreferences
                        Toast.makeText(getApplicationContext(), "Email modificat CORRECTAMENT", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
                    }
                }
            });
        }
    }
}