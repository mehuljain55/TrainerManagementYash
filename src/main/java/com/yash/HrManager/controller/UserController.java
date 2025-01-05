package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.service.TrainerService;
import com.yash.HrManager.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/register")
    public ApiResponseModel addUser(@RequestBody User user)
    {
        return userAuthorizationService.addUser(user);
    }

    @PostMapping("/login")
    private ApiResponseModel login(@RequestParam("emailId") String emailId,@RequestParam("password") String password)
    {
        return userAuthorizationService.validateUserLogin(emailId,password);
    }

    @GetMapping("/validate_token")
    public ApiResponseModel validateUserToken(@RequestParam("userId") String userId, @RequestParam("token") String token)
    {
        boolean status=userAuthorizationService.validateUserToken(userId,token);
        ApiResponseModel apiResponseModel;
        if(status)
        {
            apiResponseModel=new ApiResponseModel<>(StatusResponse.authorized,null,"Valid token");
        }
        else {
            apiResponseModel=new ApiResponseModel<>(StatusResponse.unauthorized,null,"Invalid token");
        }

        return apiResponseModel;
    }


}
