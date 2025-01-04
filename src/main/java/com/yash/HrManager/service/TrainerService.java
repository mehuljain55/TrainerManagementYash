package com.yash.HrManager.service;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.models.ApiResponseModel;

import com.yash.HrManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {

    @Autowired
    private UserRepo userRepo;

    public ApiResponseModel addUser(User user)
    {
        try {
            user.setRole(UserRoles.trainer);
            userRepo.save(user);
            return new ApiResponseModel(StatusResponse.success,null ,"Trainer Added");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel(StatusResponse.failed,null ,"Unable to add trainer");
        }
    }
}
