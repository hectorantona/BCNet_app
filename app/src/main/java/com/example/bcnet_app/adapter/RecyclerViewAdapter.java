package com.example.bcnet_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bcnet_app.R;
import com.example.bcnet_app.models.Localitzacio;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "RecyclerViewAdapter";

    private final List<Localitzacio> localitzacioList;
    private final List<Localitzacio> localitzacioListFull;

    private final Context mContext;

    public RecyclerViewAdapter(Context context, List<Localitzacio> localitzacions) {
        localitzacioList = localitzacions;
        localitzacioListFull = new ArrayList<>(localitzacions);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        //Set name de localitzacio
        ((ViewHolder)holder).mName.setText(localitzacioList.get(i).getTitle());
        ((ViewHolder)holder).category.setText(localitzacioList.get(i).getCategory());

        //Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                //imatge que estem carregant
                .load(localitzacioList.get(i).getImageUrl())
                .into(((ViewHolder)holder).image);

        defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                //Semafor
                .load(localitzacioList.get(i).getSemaforUrl())
                .into(((ViewHolder)holder).semafor);

        holder.imagecontent.setText(localitzacioList.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return localitzacioList.size();
    }

    @Override
    public Filter getFilter() {
        return FilterLocalitzacio;
    }

    private final Filter FilterLocalitzacio = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Localitzacio> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(localitzacioListFull);
            } else {
                String filterpattern = constraint.toString().toLowerCase().trim();
                for(Localitzacio l : localitzacioListFull) {
                    if(l.getTitle().toLowerCase().contains(filterpattern)){
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
        protected void publishResults(CharSequence constraint, FilterResults results) {
            localitzacioList.clear();
            localitzacioList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView mName;
        TextView category;
        TextView imagecontent;
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
            imagecontent = itemView.findViewById(R.id.image_content);
            //semafor
            semafor = itemView.findViewById(R.id.semafor);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}