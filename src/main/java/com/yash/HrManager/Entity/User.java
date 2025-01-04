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

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", officeId='" + officeId + '\'' +
                '}';
    }
}
