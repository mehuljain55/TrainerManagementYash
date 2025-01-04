package com.yash.HrManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "daily_schedule")
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    @JsonBackReference("training-weeklySchedules")
    private Training training;

    private String emailId;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "week_id", nullable = false)
    @JsonBackReference("weeklySchedule-dailySchedules")
    private WeeklySchedule weeklySchedule;


    public DailySchedule() {}

    public DailySchedule(int sno, Training training, String emailId, Date date, String description, WeeklySchedule weeklySchedule) {
        this.sno = sno;
        this.training = training;
        this.emailId = emailId;
        this.date = date;
        this.description = description;
        this.weeklySchedule = weeklySchedule;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }
}
