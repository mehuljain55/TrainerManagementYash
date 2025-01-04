package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.enums.UserRoles;
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

    private final UserRoles accessRole=UserRoles.trainer;

    @PostMapping("/register")
    public ApiResponseModel addUser(@RequestBody User user)
    {
        return trainerService.addUser(user);
    }

    @PostMapping("/login")
    private ApiResponseModel login(@RequestParam("emailId") String emailId,@RequestParam("password") String password)
    {
        return userAuthorizationService.validateUserLogin(emailId,password);
    }
}
