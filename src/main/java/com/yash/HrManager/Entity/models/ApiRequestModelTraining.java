package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ApiRequestModelTraining {
    private String token;
    private User user;
    private Training training;
    private List<Training> trainingList;
    private int trainingId;


    public ApiRequestModelTraining(String token, User user, Training training) {
        this.token = token;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }


}
