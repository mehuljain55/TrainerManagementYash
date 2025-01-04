package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.User;

public class ApiRequestModel {
    private String token;
    private User user;

    public ApiRequestModel(String token, User user) {
        this.token = token;
        this.user = user;
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
