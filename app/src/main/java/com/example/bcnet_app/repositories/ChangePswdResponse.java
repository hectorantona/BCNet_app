package com.example.bcnet_app.repositories;

public interface ChangePswdResponse {
    void changePassword (String Username, String password, Boolean message, String errormsg);

}
