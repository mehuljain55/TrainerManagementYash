package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.models.ApiRequestModelTraining;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.service.TrainingService;
import com.yash.HrManager.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private UserAuthorizationService userAuthorizationService;


    public ApiResponseModel createNewTraining(@RequestBody ApiRequestModelTraining training){
        boolean validateAccess=userAuthorizationService.validateUserToken(training.getTrainer().getEmailId(),training.getToken());
        if(validateAccess)
        {
            return trainingService.addNewTraining(training.getTraining());
        }else {
            return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
        }
    }
}
