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
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.repositories.InfoUserResponse;
import com.example.bcnet_app.viewmodels.UserViewModel;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MAINPAGE";
    private SharedPreferences mPreferences;
    private final String USERNAME_KEY = "username";
    private final String EMAIL_KEY = "email";

    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";

    private UserViewModel userViewModel;


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
                userViewModel.infouser(mPreferences.getString("username", null), new InfoUserResponse() {
                    @Override
                    public void infouser(String Username, String email, Boolean message, String errormsg) {
                        if (message) {
                            SharedPreferences.Editor sharedpreferenceseditor = mPreferences.edit();
                            sharedpreferenceseditor.putString(EMAIL_KEY, email);
                            sharedpreferenceseditor.apply();
                            Log.d(TAG, "EMAIL: " + mPreferences.getString("email", null)); //PROVES FUNCIONAMENT sharedPreferences
                            Intent startIntent = new Intent(getApplicationContext(), PerfilActivity.class);
                            startActivity(startIntent);
                        }
                    }
                });

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();

        Log.d(TAG, "USERNAME: " + mPreferences.getString("username", null)); //PROVES FUNCIONAMENT sharedPreferences




       /* mRecyclerView = findViewById(R.id.recycler_view);
        mMainActivity2ViewModel = new ViewModelProvider(this).get(MainActivity2ViewModel.class);

        mMainActivity2ViewModel.init();

        mMainActivity2ViewModel.getLocalitzacions().observe(this, new Observer<Localitzacio>() {
            @Override
            public void onChanged(List<Localitzacio> localitzacios) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecycleView();

    }

    private void initRecycleView() {
        mAdapter = new RecyclerViewAdapter(this, mMainActivity2ViewModel.getLocalitzacions().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
*/
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }*/
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:

                return true;

            case R.id.logout:
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    }
}