package com.yash.HrManager.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="weekly_schedule")
public class WeeklySchedule {
    @Id
    private int weekId;
    @Temporal(TemporalType.DATE)
    private Date weekStartDate;
    @Temporal(TemporalType.DATE)
    private Date weekEndDate;

    @OneToMany(mappedBy = "weeklySchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DailySchedule> schedules;

   @ManyToMany
    @JoinTable(name = "training_schedule", // Custom join table name
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "week_id"))
    private List<Training> trainingList;

    public WeeklySchedule(int weekId, Date weekStartDate, Date weekEndDate, List<DailySchedule> schedules) {
        this.weekId = weekId;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.schedules = schedules;
    }

    public WeeklySchedule() {
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public List<DailySchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<DailySchedule> schedules) {
        this.schedules = schedules;
    }
}
