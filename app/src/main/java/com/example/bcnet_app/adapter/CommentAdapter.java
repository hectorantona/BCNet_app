package com.example.bcnet_app.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bcnet_app.R;
import com.example.bcnet_app.models.Comment;
import com.example.bcnet_app.models.CommentResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context mContext;
    private List<Comment> result = new ArrayList<>();


    public CommentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.layout_comentitem,parent,false);
        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        //Glide.with(mContext).load(mData.get(position).getUimg()).into(holder.img_user);
        holder.name.setText(result.get(position).getUsuari());
        holder.content.setText(result.get(position).getComentari());
        //holder.date.setText(timestampToString((Long)mData.get(position).getTimestamp()));

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void setResults(CommentResponse c) {
        //Log.d(TAG, "onClick: clicked on: " + result.get(0).getContent());
        for (int i = 0; i < c.getnumelements(); ++i) {
            result.add(c.getelemi(i));
        }

        notifyDataSetChanged();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        ImageView comment_user_img;
        TextView name,content,date;

        public CommentViewHolder(View itemView) {
            super(itemView);
            comment_user_img = itemView.findViewById(R.id.comment_user_img);
            name = itemView.findViewById(R.id.comment_username);
            content = itemView.findViewById(R.id.comment_content);
            date = itemView.findViewById(R.id.comment_date);
        }
    }

    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("hh:mm",calendar).toString();
        return date;


    }

}
