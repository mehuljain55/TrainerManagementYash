package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.WeeklySchedule;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.models.ApiRequestModelDailySchedule;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.service.TrainerService;
import com.yash.HrManager.service.UserAuthorizationService;
import com.yash.HrManager.service.WeeklyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    @Autowired
    private WeeklyScheduleService weeklyScheduleService;

    private final UserRoles accessRole=UserRoles.trainer;



    @GetMapping("/generateWeek")
    private List<WeeklySchedule> weeklyScheduleList(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                    @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
    {
     return  weeklyScheduleService.generateWeeklySchedule(startDate,endDate);
    }

    @PostMapping("/viewTrainerScheduleDateRange")
    private ApiResponseModel viewTrainerDailyScheduleByDateRange(@RequestBody ApiRequestModelDailySchedule trainerSchedule)
    {
        boolean validateAccess=userAuthorizationService.validateUserAccess(trainerSchedule.getUser(),trainerSchedule.getToken(),accessRole);
        if(validateAccess)
        {
            return trainerService.viewTrainerScheduleDateRange(trainerSchedule.getUser().getEmailId(),trainerSchedule.getStartDate(),trainerSchedule.getEndDate());
        }else {
            return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
        }
    }

}
