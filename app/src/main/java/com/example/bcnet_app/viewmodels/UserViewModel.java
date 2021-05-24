package com.example.bcnet_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bcnet_app.models.User;
import com.example.bcnet_app.response.ChangeEmailResponse;
import com.example.bcnet_app.response.ChangeProfilePictureResponse;
import com.example.bcnet_app.response.ChangePswdResponse;
import com.example.bcnet_app.response.InfoUserResponse;
import com.example.bcnet_app.response.LoginResponse;
import com.example.bcnet_app.response.SignUpResponse;
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

    public void changeEmail (String username, String Password, String newEmail, ChangeEmailResponse callback){
        Repo.changeEmail(username, Password, newEmail, callback);
    }

    public void changeProfilePicture (String username, String Password, String newPicture, ChangeProfilePictureResponse callback){
        Repo.changeProfilePicture(username, Password, newPicture, callback);
    }

}
