package com.YashHrManager.Repository;

import com.YashHrManager.Entity.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyScheduleRepo extends JpaRepository<WeeklySchedule,Integer> {
}
