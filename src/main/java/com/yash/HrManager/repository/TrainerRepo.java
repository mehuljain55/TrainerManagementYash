package com.yash.HrManager.repository;

import com.yash.HrManager.Entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepo extends JpaRepository<Trainer,String> {
}
