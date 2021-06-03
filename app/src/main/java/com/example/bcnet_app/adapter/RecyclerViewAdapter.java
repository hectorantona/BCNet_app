package com.example.bcnet_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bcnet_app.R;
import com.example.bcnet_app.ViewLocalitzacio;
import com.example.bcnet_app.models.Localitzacio;
import com.example.bcnet_app.models.LocalitzacionsSearch;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> /*implements Filterable*/ {

    private static final String TAG = "CERCA";
    private List<Localitzacio> result = new ArrayList<>();
    private Context context;


    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Localitzacio localitzacio = result.get(i);

        holder.mName.setText(localitzacio.getName());
        holder.category.setText(localitzacio.getCategory());

        Glide.with(holder.itemView)
                .load(localitzacio.getImageUrl())
                .into((holder).image);

        Glide.with(holder.itemView)
                .load(localitzacio.getSemaforUrl())
                .into((holder).semafor);

        holder.puntuacioGlobal.setText(String.valueOf(localitzacio.getPuntuacioGlobal()));

        if (localitzacio.isopen(localitzacio.getHorari())){
            holder.tancat.setVisibility(View.GONE);
            holder.obert.setVisibility(View.VISIBLE);

        }
        else{
            holder.tancat.setVisibility(View.VISIBLE);
            holder.obert.setVisibility(View.GONE);
        }
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                viewlocalitzacio(localitzacio);
            }
        });
    }

    private void viewlocalitzacio (Localitzacio localitzacio) {
        Intent intent = new Intent(context, ViewLocalitzacio.class);
        intent.putExtra("id", localitzacio.getId());
        intent.putExtra("imatge", localitzacio.getImageUrl());
        intent.putExtra("nom_localitzacio", localitzacio.getName());
        intent.putExtra("content", localitzacio.getContent());
        intent.putExtra("puntuacio_global", localitzacio.getPuntuacioGlobal());
        intent.putExtra("latitud", localitzacio.getLatitud());
        intent.putExtra("longitud", localitzacio.getLongitud());
        intent.putExtra("categoria", localitzacio.getCategory());
        intent.putExtra("horari", localitzacio.getHorari());
        intent.putExtra("semafor", localitzacio.getSemaforUrl());


        context.startActivity(intent);
    }

    public void setResults(LocalitzacionsSearch l) {
        result.clear();
        result.clear();
        for (int i = 0; i < l.getnumelements(); ++i) {
            result.add(l.getelemi(i));
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView mName, category, puntuacioGlobal, obert, tancat;
        ImageView semafor;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //imatge
            image = itemView.findViewById(R.id.image);
            //Category
            category = itemView.findViewById(R.id.image_category);
            //Nom de la localitzacio
            mName = itemView.findViewById(R.id.image_name);
            //context
            //puntuacioGlobal
            puntuacioGlobal = itemView.findViewById(R.id.puntuacio_Global);
            obert = itemView.findViewById(R.id.obert);
            tancat = itemView.findViewById(R.id.tancat);
            //semafor
            semafor = itemView.findViewById(R.id.semafor);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}