package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalitzacioResponse {
    @SerializedName("result")
    @Expose
    private String error;

    public String getError() {
        return error;
    }
}
