package com.yash.HrManager.Entity;

import com.yash.HrManager.Entity.enums.TrainingStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {

    @Id
    private int trainingId;
    private String emailId;
    private String trainerName;
    private int noOfParticipant;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private TrainingStatus status;

    @ManyToMany(mappedBy = "trainingList")
    private List<WeeklySchedule> weeklySchedules;

    public Training(int trainingId, String trainerName, int noOfParticipant, Date startDate, Date endDate, TrainingStatus status) {
        this.trainingId = trainingId;
        this.trainerName = trainerName;
        this.noOfParticipant = noOfParticipant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Training() {
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public int getNoOfParticipant() {
        return noOfParticipant;
    }

    public void setNoOfParticipant(int noOfParticipant) {
        this.noOfParticipant = noOfParticipant;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TrainingStatus getStatus() {
        return status;
    }

    public void setStatus(TrainingStatus status) {
        this.status = status;
    }



    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<WeeklySchedule> getWeeklySchedules() {
        return weeklySchedules;
    }

    public void setWeeklySchedules(List<WeeklySchedule> weeklySchedules) {
        this.weeklySchedules = weeklySchedules;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
