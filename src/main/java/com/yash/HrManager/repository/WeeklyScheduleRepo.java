package com.yash.HrManager.repository;

import com.yash.HrManager.Entity.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyScheduleRepo extends JpaRepository<WeeklySchedule,Integer> {
}
