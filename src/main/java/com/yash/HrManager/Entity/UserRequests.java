package com.yash.HrManager.Entity;

import com.yash.HrManager.Entity.enums.RequestStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="userRequest")
public class UserRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    private String emailId;
    private int trainingId;
    private int dailyScheduledId;
    @Temporal(TemporalType.DATE)
    private Date validTill;
    private String reason;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    public UserRequests() {
    }

    public UserRequests(int requestId, int trainingId, String reason) {
        this.requestId = requestId;
        this.trainingId = trainingId;
        this.reason = reason;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public int getDailyScheduledId() {
        return dailyScheduledId;
    }

    public void setDailyScheduledId(int dailyScheduledId) {
        this.dailyScheduledId = dailyScheduledId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
