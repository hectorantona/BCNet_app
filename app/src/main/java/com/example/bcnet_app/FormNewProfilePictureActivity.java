package com.example.bcnet_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bcnet_app.response.ChangeProfilePictureResponse;
import com.example.bcnet_app.viewmodels.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class FormNewProfilePictureActivity extends AppCompatActivity {
    private static final String TAG = "FormNewProfilePicture";
    private EditText pass;
    private EditText newUrlProfilePicture;
    private ImageView profileImage;

    private Button BtnModifyPicture;

    private TextInputLayout mFloatLabelPassword;
    private TextInputLayout mFloatLabelNewUrlProfilePicture;

    private UserViewModel userViewModel;

    private SharedPreferences mPreferences;
    private final String USERIMAGE_KEY = "userimage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_new_profile_picture);

        pass = (EditText) findViewById(R.id.password);
        newUrlProfilePicture = (EditText) findViewById(R.id.new_Profile_picture_url);

        mFloatLabelPassword = (TextInputLayout) findViewById(R.id.float_label_password);
        mFloatLabelNewUrlProfilePicture = (TextInputLayout) findViewById(R.id.float_label_new_Profile_picture_url);

        profileImage = findViewById(R.id.imatge_perfil);
        mPreferences = getSharedPreferences("User", 0);
        String profileimgUrl = mPreferences.getString(USERIMAGE_KEY, null);
        Glide.with(this)
                .load(profileimgUrl)
                .into(profileImage);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();

        mPreferences = getSharedPreferences("User", 0);

        BtnModifyPicture = (Button) findViewById(R.id.BtnModifyProfilePicture);
        BtnModifyPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptModifyProfilePicture();
            }
        });
    }

    private void attemptModifyProfilePicture() {

        mFloatLabelPassword.setError(null);
        mFloatLabelNewUrlProfilePicture.setError(null);

        String passi = pass.getText().toString();
        String newUrlProfilePicturi = newUrlProfilePicture.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(passi)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(newUrlProfilePicturi)) {
            mFloatLabelNewUrlProfilePicture.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelNewUrlProfilePicture;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
        else {
            userViewModel.changeProfilePicture(mPreferences.getString("username", null), passi, newUrlProfilePicturi, new ChangeProfilePictureResponse() {
                @Override
                public void changeProfilePicture(String Username, String picture, Boolean message, String errormsg) {
                    if (message) {
                        SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                        sharedpreferenceseditor.putString(USERIMAGE_KEY, newUrlProfilePicturi);
                        sharedpreferenceseditor.apply();
                        Log.d(TAG, "NEW PROFILE PICTURE: " + mPreferences.getString(USERIMAGE_KEY, null)); //PROVES FUNCIONAMENT sharedPreferences
                        Toast.makeText(getApplicationContext(), "ProfilePicture modificada CORRECTAMENT", Toast.LENGTH_SHORT).show();
                        Intent startIntent = new Intent(getApplicationContext(), PerfilActivity.class);
                        startActivity(startIntent);
                    }
                    else{
                        mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
                    }
                }
            });
        }
    }
}