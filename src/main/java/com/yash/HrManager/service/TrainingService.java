package com.yash.HrManager.service;

import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.TraningStatus;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.repository.TraniningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    @Autowired
    private TraniningRepo traniningRepo;

    public ApiResponseModel addNewTraining(Training trainingRequest)
    {
        try {
          trainingRequest.setStatus(TraningStatus.PLANNED);
          Training training= traniningRepo.save(trainingRequest);
          return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to add training");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to add training");
        }
    }

}
