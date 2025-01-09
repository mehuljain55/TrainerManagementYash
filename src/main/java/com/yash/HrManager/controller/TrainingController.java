package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.models.ApiRequestModel;
import com.yash.HrManager.Entity.models.ApiRequestModelDailySchedule;
import com.yash.HrManager.Entity.models.ApiRequestModelTraining;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.service.TrainingService;
import com.yash.HrManager.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    private final UserRoles accessRole=UserRoles.trainer;

    @PostMapping("/addNewTraining")
    public ApiResponseModel createNewTraining(@RequestBody ApiRequestModelTraining training){
        System.out.println(training.getUser());
        System.out.println(training.getToken());
        boolean validateAccess=userAuthorizationService.validateUserToken(training.getUser().getEmailId(),training.getToken());
        if(validateAccess)
        {
            return trainingService.addNewTraining(training.getUser(),training.getTraining());
        }else {
            return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
        }
    }

    @PostMapping("/viewTrainingListByEmailAndStatus")
    public ApiResponseModel viewTrainingListByEmailAndStatus(@RequestBody ApiRequestModel training)
    {
        boolean validateAccess=userAuthorizationService.validateUserToken(training.getUser().getEmailId(),training.getToken());
        if(validateAccess)
        {

            return trainingService.findTrainingsByEmailAndStatus(training.getUser(),training.getTrainingStatus());
        }else {
            return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
        }
    }

    @PostMapping("/viewDailySchedule")
    public ApiResponseModel viewDailySchedule(@RequestBody ApiRequestModelDailySchedule training)
    {
        boolean validateAccess=userAuthorizationService.validateUserToken(training.getUser().getEmailId(),training.getToken());
        if(validateAccess)
        {

            return trainingService.findDailyScheduleByWeekIdAndTrainingId(training.getTrainingId());
        }else {
            return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
        }
    }

    @PostMapping("/updateDailySchedule")
    public ApiResponseModel updateDailySchedule(@RequestBody ApiRequestModelDailySchedule training)
    {
        boolean validateAccess=userAuthorizationService.validateUserToken(training.getUser().getEmailId(),training.getToken());
        if(validateAccess)
        {
            return trainingService.updateDailySchedule(training.getDailyScheduleList());
        }else {
            return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
        }
    }

    @PostMapping("/updateTrainingStatus")
    public ApiResponseModel updateTrainingStatus(@RequestBody ApiRequestModelTraining training)
    {
        boolean validateAccess=userAuthorizationService.validateUserToken(training.getUser().getEmailId(),training.getToken());
        if(validateAccess)
        {
            return trainingService.updateTrainingStatus(training.getTrainingList());
        }else {
            return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
        }
    }

}
