package com.yash.HrManager.Entity;

import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.enums.UserStatus;
import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    private String emailId;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRoles role;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String officeId;

    public User(String emailId, String name, UserRoles role, String password, UserStatus status, String officeId) {
        this.emailId = emailId;
        this.name = name;
        this.role = role;
        this.password = password;
        this.status = status;
        this.officeId = officeId;
    }

    public User() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }
}
