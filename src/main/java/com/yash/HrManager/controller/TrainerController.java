package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.WeeklySchedule;
import com.yash.HrManager.Entity.enums.UserRoles;
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

    @GetMapping("/weekDetails")
    private List<WeeklySchedule> weekDetails(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                    @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
    {
        return  weeklyScheduleService.getWeekByDates(startDate,endDate);
    }

}
