package com.yash.HrManager.service;

import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.TrainingStatus;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.repository.TraniningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private TraniningRepo traniningRepo;

    public ApiResponseModel addNewTraining(Training trainingRequest)
    {
        try {
          trainingRequest.setStatus(TrainingStatus.PLANNED);
          Training training= traniningRepo.save(trainingRequest);
          return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to add training");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to add training");
        }
    }

    public ApiResponseModel<List<Training>> findTrainingsByEmailAndStatus(User user, TrainingStatus status)
    {
        try {
            List<Training> trainings=traniningRepo.findTrainingsByEmailAndStatus(user.getEmailId(),status);
            if(trainings!=null&& trainings.size()>0)
            {
                return new ApiResponseModel<>(StatusResponse.success,trainings,"Training found");
            }else{
                return new ApiResponseModel<>(StatusResponse.not_found,null,"No traning found");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to find training");
        }
    }



}
