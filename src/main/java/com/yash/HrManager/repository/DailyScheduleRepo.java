package com.yash.HrManager.repository;

import com.yash.HrManager.Entity.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyScheduleRepo extends JpaRepository<DailySchedule,Integer> {
}
