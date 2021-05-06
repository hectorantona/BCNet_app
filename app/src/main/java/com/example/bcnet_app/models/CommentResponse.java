package com.example.bcnet_app.models;

import java.util.ArrayList;

public class CommentResponse {

    private boolean error;

    private ArrayList<Comment> comentaris;


    public CommentResponse (){
        comentaris = new ArrayList<>();
    }
    public ArrayList<Comment> getComentaris() {
        return comentaris;
    }

    //search/sorting
    public Comment getelemi (Integer i) {
        return comentaris.get(i);
    }
    public Integer getnumelements() {
        return comentaris.size();
    }
}
