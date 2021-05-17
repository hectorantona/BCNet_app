package com.example.bcnet_app.adapter;

import android.content.Context;
import android.text.format.DateFormat;
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
import com.example.bcnet_app.repositories.ComentariRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context mContext;
    private String establimentkey;
    private List<Comment> result = new ArrayList<>();
    private String nomuser;


    public CommentAdapter(Context mContext, String establimentkey, String loggeduser) {
        this.mContext = mContext;
        this.establimentkey = establimentkey;
        nomuser = loggeduser;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.layout_comentitem,parent,false);
        return new CommentViewHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment c = result.get(position);
        Log.d("Comment adapter", "Comentari de" + c.getUsuari());
        //Glide.with(mContext).load(mData.get(position).getUimg()).into(holder.img_user);
        holder.name.setText(c.getUsuari());
        holder.content.setText(c.getComentari());

        if (!c.getUsuari().equals(nomuser)) {
            Log.d("Comment adapter", "El nom no coicideix" + c.getUsuari() + "" + nomuser);
            holder.delete.setVisibility(View.GONE);
        }
        else {
            Log.d("Comment adapter", "Esborrar Comentari de" + c.getUsuari());
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletecomment(c);
                }
            });
        }
    }

    private void deletecomment(Comment c) {
        ComentariRepository.getInstance().deletecomment(c.getUsuari(), establimentkey);
        result.remove(c);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void setResults(CommentResponse c) {
        result.clear();
        //Log.d(TAG, "onClick: clicked on: " + result.get(0).getContent());
        for (int i = 0; i < c.getnumelements(); ++i) {
            result.add(c.getelemi(i));
        }

        notifyDataSetChanged();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        ImageView comment_user_img;
        TextView name,content,date;
        Button delete;

        public CommentViewHolder(View itemView) {
            super(itemView);
            comment_user_img = itemView.findViewById(R.id.comment_user_img);
            name = itemView.findViewById(R.id.comment_username);
            content = itemView.findViewById(R.id.comment_content);
            date = itemView.findViewById(R.id.comment_date);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("hh:mm",calendar).toString();
        return date;


    }

}
