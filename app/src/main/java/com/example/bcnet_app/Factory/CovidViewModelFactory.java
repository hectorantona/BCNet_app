package com.example.bcnet_app.Factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.viewmodels.CommentViewModel;
import com.example.bcnet_app.viewmodels.CovidCommentsViewModel;

public class CovidViewModelFactory implements ViewModelProvider.Factory {

    private String mParam;

    public CovidViewModelFactory(String param) {
        mParam = param;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CovidCommentsViewModel(mParam);

    }
}
