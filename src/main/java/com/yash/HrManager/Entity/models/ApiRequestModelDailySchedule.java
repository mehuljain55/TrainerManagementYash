package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.DailySchedule;
import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;

import java.util.Date;
import java.util.List;

public class ApiRequestModelDailySchedule {
    private String token;
    private User user;
    private int trainingId;
    private Date startDate;
    private Date endDate;
    private List<DailySchedule> dailyScheduleList;
    private DailySchedule dailySchedule;

    public ApiRequestModelDailySchedule(String token, User user, Date startDate, Date endDate) {
        this.token = token;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ApiRequestModelDailySchedule() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public List<DailySchedule> getDailyScheduleList() {
        return dailyScheduleList;
    }

    public void setDailyScheduleList(List<DailySchedule> dailyScheduleList) {
        this.dailyScheduleList = dailyScheduleList;
    }

    public DailySchedule getDailySchedule() {
        return dailySchedule;
    }

    public void setDailySchedule(DailySchedule dailySchedule) {
        this.dailySchedule = dailySchedule;
    }
}
