package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.Trainer;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.models.ApiRequestModelTraining;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.service.TrainerService;
import com.yash.HrManager.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    private final UserRoles userRoles=UserRoles.trainer;

    @PostMapping("/register")
    public ApiResponseModel addTrainer(@RequestBody Trainer trainer)
    {
        return  trainerService.addTrainer(trainer);
    }



}
