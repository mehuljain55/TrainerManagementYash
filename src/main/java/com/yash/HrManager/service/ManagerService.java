package com.yash.HrManager.service;

import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.TrainingStatus;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.repository.DailyScheduleRepo;
import com.yash.HrManager.repository.TraniningRepo;
import com.yash.HrManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private TraniningRepo traniningRepo;

    @Autowired
    private DailyScheduleRepo dailyScheduleRepo;

    @Autowired
    private UserRepo userRepo;

    public ApiResponseModel viewTrainingStatusWise(TrainingStatus status)
    {
        try {
            List<Training> trainingList = traniningRepo.findTrainingsByStatus(status);
            if(trainingList.size()>0)
            {
                return new ApiResponseModel<>(StatusResponse.success,trainingList,"Training found");
            }
            else {
                return new ApiResponseModel<>(StatusResponse.not_found,null,"No training found");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to fetch training");
        }
    }

    public ApiResponseModel updateTrainingStatus(int trainingId)
    {
        Optional<Training> optionalTraining=traniningRepo.findById(trainingId);
        if(optionalTraining.isPresent())
        {
            Training training=optionalTraining.get();
            training.setStatus(TrainingStatus.PLANNED);
            traniningRepo.save(training);
            return new ApiResponseModel<>(StatusResponse.success,null,"Training updated");

        }else{
            return new ApiResponseModel<>(StatusResponse.not_found,null,"Training not found");
        }
    }

}
