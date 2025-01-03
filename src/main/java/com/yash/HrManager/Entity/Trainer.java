package com.yash.HrManager.Entity;

import com.yash.HrManager.Entity.enums.UserRoles;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trainer")
public class Trainer {

    @Id
    private String trainerId;
    private String emailId;
    private String trainerName;
    private UserRoles role;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
