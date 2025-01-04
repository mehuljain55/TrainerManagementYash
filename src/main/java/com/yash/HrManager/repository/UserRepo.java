package com.yash.HrManager.repository;


import com.yash.HrManager.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
