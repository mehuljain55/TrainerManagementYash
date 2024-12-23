package com.YashHrManager.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trainer")
public class Trainer {

    @Id
    private String trainerId;
    private String trainerName;
    private String password;

    public Trainer(String trainerId, String trainerName, String password) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.password = password;
    }

    public Trainer() {
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
