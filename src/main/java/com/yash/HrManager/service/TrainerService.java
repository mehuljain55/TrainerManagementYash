package com.yash.HrManager.service;

import com.yash.HrManager.Entity.DailySchedule;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.UserRequests;
import com.yash.HrManager.Entity.WeeklySchedule;
import com.yash.HrManager.Entity.enums.RequestStatus;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.TrainingType;
import com.yash.HrManager.Entity.models.ApiResponseModel;

import com.yash.HrManager.repository.DailyScheduleRepo;
import com.yash.HrManager.repository.UserRepo;
import com.yash.HrManager.repository.UserRequestRepository;
import com.yash.HrManager.repository.WeeklyScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
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

    @Autowired
    private UserRequestRepository userRequestRepo;

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
        try {
            for(DailySchedule schedule:dailySchedules)
            {
                WeeklySchedule weeklySchedule=weeklyScheduleService.generateWeeklySchedule(schedule.getDate(),schedule.getDate()).get(0);
                schedule.setWeeklySchedule(weeklySchedule);
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
    public ApiResponseModel createEditRequest(UserRequests userRequests,User user)
    {
        try{
            List<UserRequests> userRequestsList=userRequestRepo.findUserEditRequestByTrainingId(userRequests.getDailyScheduledId(),RequestStatus.pending);
            if(userRequestsList.size()>0)
            {
                return new ApiResponseModel<>(StatusResponse.failed,null,"Request already in pending state");
            }
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            Date validTill = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
            userRequests.setEmailId(user.getEmailId());
            userRequests.setValidTill(validTill);
            userRequests.setStatus(RequestStatus.pending);
            userRequestRepo.save(userRequests);
            return new ApiResponseModel<>(StatusResponse.success,null,"Request Created");

        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to create edit request");
        }
    }

}
