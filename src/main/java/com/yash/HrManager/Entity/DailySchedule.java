package com.yash.HrManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "daily_schedule")
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_schedule_id", nullable = false)
    @JsonBackReference
    private WeeklySchedule weeklySchedule;

    private String emailId;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    public DailySchedule(int sno, WeeklySchedule weeklySchedule, String emailId, Date date, String description) {
        this.sno = sno;
        this.weeklySchedule = weeklySchedule;
        this.emailId = emailId;
        this.date = date;
        this.description = description;
    }

    public DailySchedule() {
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
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
}
