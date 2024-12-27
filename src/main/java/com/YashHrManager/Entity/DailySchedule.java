package com.YashHrManager.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="daily_schedule")
public class DailySchedule {

    @Id
    private int sno;

    private Training training;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "week_id", nullable = false)
    private WeeklySchedule weeklySchedule;


    public DailySchedule(int sno, String trainerId, String trainerName, Date date, String description, WeeklySchedule weeklySchedule) {
        this.sno = sno;
        this.date = date;
        this.description = description;
        this.weeklySchedule = weeklySchedule;
    }

    public DailySchedule() {
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
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
