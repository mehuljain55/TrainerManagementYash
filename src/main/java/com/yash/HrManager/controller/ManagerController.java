package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.models.ApiRequestModel;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {

     @Autowired
     private UserAuthorizationService userAuthorizationService;



}
