package com.yash.HrManager.service;

import com.yash.HrManager.Entity.WeeklySchedule;
import com.yash.HrManager.repository.WeeklyScheduleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeeklyScheduleService {

    @Autowired
    private WeeklyScheduleRepo weeklyScheduleRepo;

    @Transactional
    public List<WeeklySchedule> generateWeeklySchedule(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        Date normalizedStartDate = calendar.getTime();
        calendar.setTime(endDate);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        Date normalizedEndDate = calendar.getTime();

        List<WeeklySchedule> newSchedules = new ArrayList<>();
        calendar.setTime(normalizedStartDate);
        while (!calendar.getTime().after(normalizedEndDate)) {
            Date weekStartDate = calendar.getTime(); // Start of the week (Monday)
            calendar.add(Calendar.DAY_OF_MONTH, 4);  // Move to Friday
            Date weekEndDate = calendar.getTime();   // End of the week (Friday)

            // Check if the week exists in the database
            if (!weeklyScheduleRepo.existsByWeekStartDateAndWeekEndDate(weekStartDate, weekEndDate)) {
                WeeklySchedule schedule = new WeeklySchedule();
                schedule.setWeekStartDate(weekStartDate);
                schedule.setWeekEndDate(weekEndDate);
                newSchedules.add(schedule);
            }

            // Move to the next Monday
            calendar.add(Calendar.DAY_OF_MONTH, 3);
        }

        weeklyScheduleRepo.saveAll(newSchedules);

        return getWeekByDates(startDate, endDate);
    }

    public List<WeeklySchedule> getWeekByDates(Date startDate, Date endDate) {
        return weeklyScheduleRepo.findWeeksByDateRange(startDate, endDate);
    }
}
