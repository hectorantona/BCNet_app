package com.example.bcnet_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bcnet_app.R;
import com.example.bcnet_app.models.Comment;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.models.DadesCovid;
import com.example.bcnet_app.models.DadesCovidResponse;
import com.example.bcnet_app.repositories.ComentariRepository;
import com.example.bcnet_app.repositories.DadesCovidRepository;

import java.util.ArrayList;
import java.util.List;

public class CovidCommentAdapter extends RecyclerView.Adapter<CovidCommentAdapter.CovidCommentViewHolder> {
    private Context mContext;
    private String establimentkey;
    private List<DadesCovid> result = new ArrayList<>();
    private String nomuser;

    public CovidCommentAdapter(Context mContext, String establimentkey, String loggeduser) {
        this.mContext = mContext;
        this.establimentkey = establimentkey;
        nomuser = loggeduser;
    }

    @NonNull
    @Override
    public CovidCommentAdapter.CovidCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.layout_covidcomentitem,parent,false);
        return new CovidCommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidCommentAdapter.CovidCommentViewHolder holder, int position) {
        DadesCovid dc = result.get(position);
        Log.d("COVID Comment adapter", "Comentari de" + dc.getUsuari());

        holder.name.setText(dc.getUsuari());
        holder.content.setText(dc.getComentari());
        holder.date.setText(dc.getDate());

        if (!dc.getUsuari().equals(nomuser)) {
            Log.d("Comment adapter", "El nom no coicideix" + dc.getUsuari() + "" + nomuser);
            holder.delete.setVisibility(View.GONE);
        }
        else {
            Log.d("Comment adapter", "Esborrar Comentari de" + dc.getUsuari());
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletecovidcomment(dc);
                }
            });
        }

    }

    private void deletecovidcomment(DadesCovid dc) {
        DadesCovidRepository.getInstance().deleteCovidComment(dc.getUsuari(), establimentkey); //FALTA QUE BACK CREI FUNCIO DELETE COVIDCOMMENT
        result.remove(dc);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void setResults(DadesCovidResponse dcr) {
        result.clear();
        //Log.d(TAG, "onClick: clicked on: " + result.get(0).getContent());
        for (int i = 0; i < dcr.getnumelements(); ++i) {
            result.add(dcr.getelemi(i));
        }

        notifyDataSetChanged();
    }

    public class CovidCommentViewHolder extends RecyclerView.ViewHolder {

        ImageView comment_user_img, comment_user_semafor;
        TextView name,content,date;
        Button delete;

        public CovidCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_user_img = itemView.findViewById(R.id.comment_user_img);
            comment_user_semafor = itemView.findViewById(R.id.comment_user_semafor);
            name = itemView.findViewById(R.id.comment_username);
            content = itemView.findViewById(R.id.comment_content);
            date = itemView.findViewById(R.id.comment_date);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
