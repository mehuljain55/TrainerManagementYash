package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.Trainer;

public class UserLoginModel {
    private String token;
    private Trainer trainer;

    public UserLoginModel(Trainer trainer,String token) {
        this.token = token;
        this.trainer = trainer;
    }

    public UserLoginModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
