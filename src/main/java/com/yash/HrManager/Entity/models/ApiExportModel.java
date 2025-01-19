package com.yash.HrManager.Entity.models;

import com.yash.HrManager.Entity.DailySchedule;
import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.WeeklySchedule;

import java.util.List;

public class ApiExportModel {
    private String token;
    private User user;
    private List<Training> trainingList;
    private List<WeeklySchedule> weeklyScheduleList;
    private List<DailySchedule> dailyScheduleList;
    private String userId;
    private int weekId;

    public ApiExportModel(String token, User user, List<Training> trainingList, List<WeeklySchedule> weeklyScheduleList, List<DailySchedule> dailyScheduleList) {
        this.token = token;
        this.user = user;
        this.trainingList = trainingList;
        this.weeklyScheduleList = weeklyScheduleList;
        this.dailyScheduleList = dailyScheduleList;
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

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    public List<WeeklySchedule> getWeeklyScheduleList() {
        return weeklyScheduleList;
    }

    public void setWeeklyScheduleList(List<WeeklySchedule> weeklyScheduleList) {
        this.weeklyScheduleList = weeklyScheduleList;
    }

    public List<DailySchedule> getDailyScheduleList() {
        return dailyScheduleList;
    }

    public void setDailyScheduleList(List<DailySchedule> dailyScheduleList) {
        this.dailyScheduleList = dailyScheduleList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
}
