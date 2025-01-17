package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.User;

public class UserLoginModel {
    private String token;
    private User user;

    public UserLoginModel(User user,String token) {
        this.token = token;
        this.user = user;
    }

    public UserLoginModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
