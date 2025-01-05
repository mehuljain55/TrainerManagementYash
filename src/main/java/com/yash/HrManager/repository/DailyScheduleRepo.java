package com.yash.HrManager.repository;

import com.yash.HrManager.Entity.DailySchedule;
import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.WeeklySchedule;
import com.yash.HrManager.Entity.enums.TrainingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyScheduleRepo extends JpaRepository<DailySchedule,Integer> {

    @Query("SELECT d FROM DailySchedule d WHERE d.weeklySchedule.weekId = :weekId AND d.trainingId = :trainingId")
    List<DailySchedule> findDailyScheduleByWeekIdAndTrainingId(@Param("weekId") int weekId, @Param("trainingId") int trainingId);
}
