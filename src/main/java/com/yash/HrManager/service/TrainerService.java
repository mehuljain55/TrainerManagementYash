package com.yash.HrManager.service;

import com.yash.HrManager.Entity.DailySchedule;
import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.models.ApiResponseModel;

import com.yash.HrManager.repository.DailyScheduleRepo;
import com.yash.HrManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DailyScheduleRepo dailyScheduleRepo;

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








}
