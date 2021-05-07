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
    // private List <Localitzacio> localitzacioList = new ArrayList<>();
    // private final List<Localitzacio> localitzacioListFull;


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

        //Set name de localitzacio
        holder.mName.setText(localitzacio.getName());
        holder.category.setText(localitzacio.getCategory());

        Glide.with(holder.itemView)
                .load(localitzacio.getImageUrl())
                .into((holder).image);

        Glide.with(holder.itemView)
                .load(localitzacio.getSemaforUrl())
                .into((holder).semafor);

        holder.puntuacioGlobal.setText(localitzacio.getPuntuacioGlobal());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: clicked on: " + localitzacioList.get(i).getContent());
                //Toast.makeText(mContext, localitzacioList.get(i).getContent(), Toast.LENGTH_SHORT).show();
                viewlocalitzacio(localitzacio);
            }
        });
    }

    private void viewlocalitzacio (Localitzacio localitzacio) {
        Intent intent = new Intent(context, ViewLocalitzacio.class);
        intent.putExtra("imatge", localitzacio.getImageUrl());
        intent.putExtra("nom_localitzacio", localitzacio.getName());
        intent.putExtra("content", localitzacio.getContent());
        intent.putExtra("puntuacio_global", localitzacio.getPuntuacioGlobal());

        context.startActivity(intent);
    }

    //Canviar el nom d'aquesta
   /* public void setResults(List<Localitzacio> results) {
        //Log.d(TAG, "onClick: clicked on: " + result.get(0).getContent());
        this.result = results;

        notifyDataSetChanged();
    }*/

    public void setResults(LocalitzacionsSearch l) {
        result.clear();
        //Log.d(TAG, "onClick: clicked on: " + result.get(0).getContent());
        for (int i = 0; i < l.getnumelements(); ++i) {
            result.add(l.getelemi(i));
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    /*@Override
    public Filter getFilter() {
        return FilterLocalitzacio;
    }

    private final Filter FilterLocalitzacio = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Localitzacio> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(result);
            } else {
                String filterpattern = constraint.toString().toLowerCase().trim();
                for(Localitzacio l : result) {
                    if(l.getName().toLowerCase().contains(filterpattern)){
                        filteredList.add(l);
                    }
                    else if(l.getCategory().toLowerCase().contains(filterpattern)){
                        filteredList.add(l);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        public void publishResults(CharSequence constraint, FilterResults results) {
            localitzacioList.clear();
            localitzacioList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };*/



    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView mName;
        TextView category;
        //TextView imagecontent;
        TextView puntuacioGlobal;
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
            //imagecontent = itemView.findViewById(R.id.image_content);
            //puntuacioGlobal
            puntuacioGlobal = itemView.findViewById(R.id.puntuacio_Global);
            //semafor
            semafor = itemView.findViewById(R.id.semafor);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}