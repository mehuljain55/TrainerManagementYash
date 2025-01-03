package com.yash.HrManager.service;

import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.Entity.Trainer;
import com.yash.HrManager.repository.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepo trainerRepo;

    public ApiResponseModel addTrainer(Trainer trainer)
    {
        try {
            trainerRepo.save(trainer);
            return new ApiResponseModel(StatusResponse.success,null ,"Trainer Added");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel(StatusResponse.failed,null ,"Unable to add trainer");
        }
    }
}
