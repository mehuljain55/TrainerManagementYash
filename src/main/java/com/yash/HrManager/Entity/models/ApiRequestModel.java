package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.UserRequests;
import com.yash.HrManager.Entity.enums.TrainingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ApiRequestModel {
    private String token;
    private User user;

    @Enumerated(EnumType.STRING)
    private TrainingStatus trainingStatus;

    private UserRequests userRequests;

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

    public TrainingStatus getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(TrainingStatus trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public UserRequests getUserRequests() {
        return userRequests;
    }

    public void setUserRequests(UserRequests userRequests) {
        this.userRequests = userRequests;
    }
}
