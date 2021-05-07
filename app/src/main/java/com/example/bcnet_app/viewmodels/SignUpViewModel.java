package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.User;
import com.example.bcnet_app.repositories.UserRepository;

public class SignUpViewModel extends ViewModel {

    private static final String TAG = "SIGNUPViewModel";

    private LiveData<User> UserLiveData;
    private UserRepository Repo;

    public void init () {
        Repo = new UserRepository();
    }

    public LiveData<User> getUser () {
        return UserLiveData;
    }

    public void signup (String email, String username, String password) {
        Repo.signup(email, username, password);
    }

}
