package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.User;


public class ApiRequestModelManager {
    private String token;
    private User user;

    private int trainingId;

    public ApiRequestModelManager(String token, User user, int trainingId) {
        this.token = token;
        this.user = user;
        this.trainingId = trainingId;
    }

    public ApiRequestModelManager() {
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

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }
}
