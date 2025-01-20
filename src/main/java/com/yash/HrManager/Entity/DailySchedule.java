package com.yash.HrManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yash.HrManager.Entity.enums.ModfiyStatus;
import com.yash.HrManager.Entity.enums.TrainerAttendance;
import com.yash.HrManager.Entity.enums.TrainingType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "daily_schedule")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "sno")
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weekly_schedule_id", nullable = false)
    @JsonBackReference
    private WeeklySchedule weeklySchedule;

    private String day;

    private String emailId;

    private int trainingId;

    @Enumerated(EnumType.STRING)
    private TrainingType type;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    @Transient
    private int weekScheduleId;

    @Enumerated(EnumType.STRING)
    private TrainerAttendance trainerAttendance;

    @Enumerated(EnumType.STRING)
    private ModfiyStatus modfiyStatus;

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

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public TrainerAttendance getTrainerAttendance() {
        return trainerAttendance;
    }

    public void setTrainerAttendance(TrainerAttendance trainerAttendance) {
        this.trainerAttendance = trainerAttendance;
    }

    public int getWeekScheduleId() {
        return weekScheduleId;
    }

    public void setWeekScheduleId(int weekScheduleId) {
        this.weekScheduleId = weekScheduleId;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public ModfiyStatus getModfiyStatus() {
        return modfiyStatus;
    }

    public void setModfiyStatus(ModfiyStatus modfiyStatus) {
        this.modfiyStatus = modfiyStatus;
    }

    @Override
    public String toString() {
        return "DailySchedule{" +
                "sno=" + sno +
                ", weeklySchedule=" + weeklySchedule +
                ", day='" + day + '\'' +
                ", emailId='" + emailId + '\'' +
                ", trainingId=" + trainingId +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
