package com.yash.HrManager.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "weekly_schedule")
public class WeeklySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weekId;

    @Temporal(TemporalType.DATE)
    @Column(unique = true, nullable = false)
    private Date weekStartDate;

    @Temporal(TemporalType.DATE)
    @Column(unique = true, nullable = false)
    private Date weekEndDate;

    @OneToMany(mappedBy = "weeklySchedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DailySchedule> schedules;

    @ManyToMany(mappedBy = "weeklySchedules", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Training> trainingList;

    public WeeklySchedule(int weekId, Date weekStartDate, Date weekEndDate, List<DailySchedule> schedules, List<Training> trainingList) {
        this.weekId = weekId;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.schedules = schedules;
        this.trainingList = trainingList;
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

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
}
