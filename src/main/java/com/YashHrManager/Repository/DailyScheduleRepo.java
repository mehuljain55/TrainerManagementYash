package com.YashHrManager.Repository;

import com.YashHrManager.Entity.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyScheduleRepo extends JpaRepository<DailySchedule,Integer> {
}
