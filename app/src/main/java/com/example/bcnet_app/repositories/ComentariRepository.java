 package com.example.bcnet_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.ViewLocalitzacio;
import com.example.bcnet_app.api.CommentService;
import com.example.bcnet_app.models.Comment;
import com.example.bcnet_app.models.CommentResponse;
import com.example.bcnet_app.response.newCommentResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class ComentariRepository {
    private static final String COMMENT_SEARCH_SERVICE_BASE_URL = "https://us-central1-bcnet-backend.cloudfunctions.net/";
    private static final String TAG = "REPO COMMENTS";
     private ViewLocalitzacio view;
     private CommentService commentService;
     private MutableLiveData<CommentResponse> commentResponseLiveData;
     private MutableLiveData<com.example.bcnet_app.models.Comment> commentLiveData;
     private String error = "true";
    //Singleton patern
    private static ComentariRepository instance;

    public static ComentariRepository getInstance() {
        if (instance == null) {
            instance = new ComentariRepository();
        }
        return instance;
    }

    private ComentariRepository() {

        view = new ViewLocalitzacio();
        commentResponseLiveData = new MutableLiveData<>();
        commentLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //per fer debug
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        commentService = new Retrofit.Builder()
                .baseUrl(COMMENT_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CommentService.class);
    }

    public void searchComments(String localitzaciokey) {
        commentService.searchcomments(localitzaciokey)
                .enqueue(new Callback<CommentResponse>() {
                    @Override
                    public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                        if (response.body() != null) {
                            //LocalitzacioResponse l = response.body();
                            //Log.d(TAG, "CORRECTE: " + l.getTotalItems());
                            commentResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentResponse> call, Throwable t) {
                        commentResponseLiveData.postValue(null);
                    }
                });
    }

    public void deletecomment (String nomuser, String idlocalitzacio) {
        commentService.deletecomment(nomuser, idlocalitzacio)
                .enqueue(new Callback<CommentResponse>() {
                    @Override
                    public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<CommentResponse> call, Throwable t) {

                    }
                });
    }

    public void newComment (String idlocalitzacio, String nomuser, String comment, String valoracio, newCommentResponse callback) {
        //Canviar establiment2 per nom localitzacio
        commentService.newComment(nomuser, idlocalitzacio, comment, valoracio)
                .enqueue(new Callback<com.example.bcnet_app.models.Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        if (response.body() != null) {
                            //LocalitzacioResponse l = response.body();
                            //Log.d(TAG, "CORRECTE: " + l.getTotalItems());
                            error = response.body().getCorrecte();
                            Log.d(TAG, "fallada " + error);
                            callback.updatecomments(error.equals("true"));
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.bcnet_app.models.Comment> call, Throwable t) {
                        commentResponseLiveData.postValue(null);
                    }
                });
    }


    public LiveData<CommentResponse> getcomments() {

        return commentResponseLiveData;
    }

    public boolean correcte() {
        if (error.equals("true")) return true;
        else return false;
    }

}
