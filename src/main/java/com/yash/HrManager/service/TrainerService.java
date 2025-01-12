package com.yash.HrManager.service;

import com.yash.HrManager.Entity.DailySchedule;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.WeeklySchedule;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.TrainingType;
import com.yash.HrManager.Entity.models.ApiResponseModel;

import com.yash.HrManager.repository.DailyScheduleRepo;
import com.yash.HrManager.repository.UserRepo;
import com.yash.HrManager.repository.WeeklyScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DailyScheduleRepo dailyScheduleRepo;

    @Autowired
    private WeeklyScheduleRepo weeklyScheduleRepo;

    @Autowired
    private WeeklyScheduleService weeklyScheduleService;

    public ApiResponseModel<List<DailySchedule>> viewTrainerScheduleDateRange(String emailId, Date startDate,Date endDate)
    {
       List<DailySchedule> dailySchedules=dailyScheduleRepo.findDailyScheduleUserDateRange(emailId,startDate,endDate);
       if(dailySchedules.size()>0)
       {
           return new ApiResponseModel<>(StatusResponse.success,dailySchedules,"Daily Schedule Found");
       }else{
           return new ApiResponseModel<>(StatusResponse.not_found,null,"Daily Schedule not found");
       }
    }

    public ApiResponseModel addTrainerDailySchedule(List<DailySchedule> dailySchedules, User user)
    {
        List<DailySchedule> dailyScheduleList=new ArrayList<>();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        try {
            for(DailySchedule schedule:dailySchedules)
            {
                WeeklySchedule weeklySchedule=weeklyScheduleService.generateWeeklySchedule(schedule.getDate(),schedule.getDate()).get(0);
                schedule.setWeeklySchedule(weeklySchedule);
                schedule.setDay(dayFormat.format(schedule.getDate()));
                schedule.setEmailId(user.getEmailId());
                schedule.setType(TrainingType.ROUTINE);
                dailyScheduleList.add(schedule);
            }
            dailyScheduleRepo.saveAll(dailyScheduleList);
            return new ApiResponseModel<>(StatusResponse.success,null,"Schedule Added");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel<>(StatusResponse.failed,null,"Error in adding daily schedules");
        }
    }

}
