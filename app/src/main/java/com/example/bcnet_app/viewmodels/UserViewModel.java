package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.User;
import com.example.bcnet_app.repositories.ChangePswdResponse;
import com.example.bcnet_app.repositories.InfoUserResponse;
import com.example.bcnet_app.repositories.LoginResponse;
import com.example.bcnet_app.repositories.SignUpResponse;
import com.example.bcnet_app.repositories.UserRepository;

public class UserViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";
    private LiveData<User>  UserLiveData;
    private LiveData<String> UsernameLiveData;


    private UserRepository Repo;

    public void init () {
        Repo = new UserRepository();
    }

    public LiveData<User> getUser () {
        return UserLiveData;
    }

    public LiveData<String> getUsername() {
        return UsernameLiveData;
    }


    public void login (String username, String password, LoginResponse callback) {
        Repo.login(username, password, callback);
    }

    public void signup (String username, String password, String email, SignUpResponse callback){
        Repo.signup(username, password, email, callback);
    }

    public void infouser (String username, InfoUserResponse callback){
        Repo.infouser(username, callback);
    }

    public void changePassword (String username, String oldPassword, String newPassword, ChangePswdResponse callback){
        Repo.changePassword(username, oldPassword, newPassword, callback);
    }

}
