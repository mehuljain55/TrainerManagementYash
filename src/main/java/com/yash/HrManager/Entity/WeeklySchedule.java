package com.yash.HrManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "weekly_schedule")
@JsonIgnoreProperties({"trainingList"})
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

    @ManyToMany(mappedBy = "weeklySchedules", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Training> trainingList;

    // Getters and Setters
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

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
}
