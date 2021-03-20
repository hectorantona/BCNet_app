package com.example.bcnet_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bcnet_app.adapter.RecyclerViewAdapter;
import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.viewmodels.MainActivity2ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private MainActivity2ViewModel mMainActivity2ViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecyclerView = findViewById(R.id.recycler_view);
        mMainActivity2ViewModel = ViewModelProviders.of(this).get(MainActivity2ViewModel.class);

        mMainActivity2ViewModel.init();

        mMainActivity2ViewModel.getLocalitzacions().observe(this, new Observer<List<Localitzacio>>() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

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
    }
}