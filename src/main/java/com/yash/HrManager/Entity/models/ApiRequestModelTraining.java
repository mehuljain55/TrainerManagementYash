package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.Trainer;
import com.yash.HrManager.Entity.Training;

public class ApiRequestModelTraining {
    private String token;
    private Trainer trainer;
    private Training training;

    public ApiRequestModelTraining(String token, Trainer trainer, Training training) {
        this.token = token;
        this.trainer = trainer;
        this.training = training;
    }

    public ApiRequestModelTraining() {
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

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
