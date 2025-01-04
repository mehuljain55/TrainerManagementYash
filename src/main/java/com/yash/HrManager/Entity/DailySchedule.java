package com.yash.HrManager.Entity;

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
    private Training training;

    private String emailId;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "week_id", nullable = false)
    private WeeklySchedule weeklySchedule;

    public DailySchedule(int sno, Training training, String emailId, Date date, String description, WeeklySchedule weeklySchedule) {
        this.sno = sno;
        this.training = training;
        this.emailId = emailId;
        this.date = date;
        this.description = description;
        this.weeklySchedule = weeklySchedule;
    }

    public DailySchedule() {
    }

    public int getSno() {
        return sno;
    }

    public Training getTraining() {
        return training;
    }

    public String getEmailId() {
        return emailId;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }
}

