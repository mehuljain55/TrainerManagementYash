package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.models.ApiRequestModel;
import com.yash.HrManager.Entity.models.ApiRequestModelTraining;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.service.ManagerService;
import com.yash.HrManager.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {

     @Autowired
     private UserAuthorizationService userAuthorizationService;

     @Autowired
     private ManagerService managerService;

     private final UserRoles accessRole=UserRoles.manager;


     @PostMapping("/viewAllTrainingStatusWise")
     public ApiResponseModel viewAllTrainingStatusWise(@RequestBody ApiRequestModel apiRequestModel)
     {
          boolean validateAccess=userAuthorizationService.validateUserAccess(apiRequestModel.getUser(),apiRequestModel.getToken(),accessRole);
          if(validateAccess)
          {
               return managerService.viewTrainingStatusWise(apiRequestModel.getTrainingStatus());
          }else {
               return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
          }
     }

     public ApiResponseModel updateTrainingStatus(@RequestBody ApiRequestModelTraining apiRequestModelTraining)
     {
          boolean validateAccess=userAuthorizationService.validateUserAccess(apiRequestModelTraining.getUser(),apiRequestModelTraining.getToken(),accessRole);
          if(validateAccess)
          {
               return managerService.updateTrainingStatus(apiRequestModelTraining.getTrainingId());
          }else {
               return new ApiResponseModel(StatusResponse.unauthorized, null, "Unauthorized Access");
          }
     }



}
