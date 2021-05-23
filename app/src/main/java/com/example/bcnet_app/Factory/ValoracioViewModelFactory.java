package com.example.bcnet_app.Factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bcnet_app.viewmodels.CommentViewModel;

public class ValoracioViewModelFactory implements ViewModelProvider.Factory {

        private String mParam;

        public ValoracioViewModelFactory(String param) {
            mParam = param;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new CommentViewModel(mParam);
        }

}
