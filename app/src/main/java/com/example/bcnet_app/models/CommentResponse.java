package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentResponse {

    @SerializedName("result")
    @Expose
    private boolean error;

    @SerializedName("value")
    @Expose
    private ArrayList<Comment> comentaris;


    public CommentResponse (){
        comentaris = new ArrayList<>();
    }
    public ArrayList<Comment> getComentaris() {
        return comentaris;
    }

    public Comment getelemi (Integer i) {
        return comentaris.get(i);
    }
    public Integer getnumelements() {
        return comentaris.size();
    }
}
