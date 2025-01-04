package com.yash.HrManager.repository;

import com.yash.HrManager.Entity.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WeeklyScheduleRepo extends JpaRepository<WeeklySchedule,Integer> {
    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END " +
            "FROM WeeklySchedule w WHERE w.weekStartDate = :weekStartDate AND w.weekEndDate = :weekEndDate")
    boolean existsByWeekStartDateAndWeekEndDate(@Param("weekStartDate") Date weekStartDate,
                                                @Param("weekEndDate") Date weekEndDate);

    @Query("SELECT w FROM WeeklySchedule w " +
            "WHERE (w.weekStartDate BETWEEN :startDate AND :endDate) " +
            "OR (w.weekEndDate BETWEEN :startDate AND :endDate) " +
            "OR (w.weekStartDate <= :startDate AND w.weekEndDate >= :endDate)")
    List<WeeklySchedule> findWeeksByDateRange(@Param("startDate") Date startDate,
                                              @Param("endDate") Date endDate);
}

