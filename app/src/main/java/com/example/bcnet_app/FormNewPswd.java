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

import com.example.bcnet_app.repositories.ChangePswdResponse;
import com.example.bcnet_app.repositories.SignUpResponse;
import com.example.bcnet_app.viewmodels.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class FormNewPswd extends AppCompatActivity {
    private static final String TAG = "FormNewPswd";

    private EditText oldpass;
    private EditText newpass;
    private EditText reppass;

    private Button BtnModify;

    private TextInputLayout mFloatLabelOldPassword;
    private TextInputLayout mFloatLabelNewPassword;
    private TextInputLayout mFloatLabelRepPassword;

    private UserViewModel userViewModel;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    private final String PASSWORD_KEY = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_new_pswd);

        oldpass = (EditText) findViewById(R.id.old_password);
        newpass = (EditText) findViewById(R.id.new_password);
        reppass = (EditText) findViewById(R.id.rep_password);

        mFloatLabelOldPassword = (TextInputLayout) findViewById(R.id.float_label_old_password);
        mFloatLabelNewPassword = (TextInputLayout) findViewById(R.id.float_label_new_password);
        mFloatLabelRepPassword = (TextInputLayout) findViewById(R.id.float_label_rep_password);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        BtnModify = (Button)findViewById(R.id.BtnModify);
        BtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptModifyPassword();
            }
        });
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    private boolean doPasswordMatch(String newpass, String rep_pass) { return newpass.equals(rep_pass); }


    private void attemptModifyPassword() {

        mFloatLabelOldPassword.setError(null);
        mFloatLabelNewPassword.setError(null);
        mFloatLabelRepPassword.setError(null);

        String oldpassi = oldpass.getText().toString();
        String newpassi = newpass.getText().toString();
        String reppassi = reppass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(oldpassi)) {
            mFloatLabelOldPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelOldPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(newpassi)) {
            mFloatLabelNewPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelNewPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(reppassi)) {
            mFloatLabelRepPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelRepPassword;
            cancel = true;
        } else if (!doPasswordMatch(newpassi, reppassi)) {
            mFloatLabelRepPassword.setError((getString(R.string.unmatched_passwords)));
            focusView = mFloatLabelRepPassword;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
        else{
            userViewModel.changePassword(mPreferences.getString("username", null), oldpassi, newpassi, new ChangePswdResponse() {
                @Override
                public void changePassword(String Username, String password, Boolean message, String errormsg) {
                    if (message) {
                        SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                        sharedpreferenceseditor.putString(PASSWORD_KEY, newpassi);
                        sharedpreferenceseditor.apply();
                        Log.d(TAG, "NEW PASSWORD: " + mPreferences.getString("password", null)); //PROVES FUNCIONAMENT sharedPreferences
                        Toast.makeText(getApplicationContext(), "Contrassenya modificada CORRECTAMENT", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

}