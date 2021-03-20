package com.example.bcnet_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.R;
import com.example.bcnet_app.models.Localitzacio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.recyclerview.internal.ViewHelper;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<Localitzacio> mLocalitzacions = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Localitzacio> localitzacions) {
        mLocalitzacions = localitzacions;
        this. mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        //Set name de localitzacio
        ((ViewHolder)holder).mName.setText(mLocalitzacions.get(i).getTitle());

        //Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                //imatge que estem carregant
                .load(mLocalitzacions.get(i).getImageUrl())
                .into(((ViewHolder)holder).image);
        holder.imageurl.setText(mLocalitzacions.get(i).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mLocalitzacions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView mName;
        TextView imageurl;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
            imageurl = itemView.findViewById(R.id.image_url);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
