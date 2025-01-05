package com.yash.HrManager.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yash.HrManager.Entity.enums.TrainingStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "training_weekly_schedule",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "weekly_schedule_id")
    )
    @JsonManagedReference
    private List<WeeklySchedule> weeklySchedules;

    public Training(int trainingId, String emailId, String trainerName, int noOfParticipant, String description, Date startDate, Date endDate, TrainingStatus status, List<WeeklySchedule> weeklySchedules) {
        this.trainingId = trainingId;
        this.emailId = emailId;
        this.trainerName = trainerName;
        this.noOfParticipant = noOfParticipant;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.weeklySchedules = weeklySchedules;
    }

    public Training() {
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<WeeklySchedule> getWeeklySchedules() {
        return weeklySchedules;
    }

    public void setWeeklySchedules(List<WeeklySchedule> weeklySchedules) {
        this.weeklySchedules = weeklySchedules;
    }
}
