package com.example.bcnet_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mWebUrl = new ArrayList<>();
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(TAG, "onCreate: started.");

        initImagesBitmap();

    }

    private void initImagesBitmap () {
        Log.d(TAG, "initImagesBitmap: preparing bitmaps.");

        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("Dos cuiners");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("McDonalds");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("Burguer King");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("AutoMcDonalds");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("Aaaaaaaaaaaaaaaaaaa");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("sbsdhfbsd");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("7");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        mImageUrls.add("https://www.4webs.es/blog/wp-content/uploads/2019/02/urls-que-es.jpg");
        mNames.add("8");
        mWebUrl.add("url:https:www/7ssas./fnjsdjfsd.com");
        initRecycleView();
    }

    private void initRecycleView() {
        Log.d(TAG, "initRecycleView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mWebUrl);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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