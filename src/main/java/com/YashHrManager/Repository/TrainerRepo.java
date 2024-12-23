package com.YashHrManager.Repository;

import com.YashHrManager.Entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepo extends JpaRepository<Trainer,String> {
}
