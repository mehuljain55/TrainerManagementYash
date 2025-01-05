package com.yash.HrManager.service;

import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.WeeklySchedule;
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

    @Autowired
    private WeeklyScheduleService weeklyScheduleService;

    public ApiResponseModel addNewTraining(User user,Training trainingRequest)
    {
        try {
          List<WeeklySchedule> weeklyScheduleList=weeklyScheduleService.generateWeeklySchedule(trainingRequest.getStartDate(),trainingRequest.getEndDate());
          trainingRequest.setStatus(TrainingStatus.PLANNED);
          trainingRequest.setEmailId(user.getEmailId());
          trainingRequest.setTrainerName(user.getName());
          trainingRequest.setWeeklySchedules(weeklyScheduleList);
            System.out.println(trainingRequest);
          Training training= traniningRepo.save(trainingRequest);
          return new ApiResponseModel<>(StatusResponse.success,null,"Training Added");
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
            System.out.println(trainings.size());
            System.out.println(user.getEmailId()+" "+status);
            if(trainings!=null&& trainings.size()>0)
            {

                return new ApiResponseModel<>(StatusResponse.success,trainings,"Training found");
            }else{
                return new ApiResponseModel<>(StatusResponse.not_found,null,"No training found");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel<>(StatusResponse.failed,null,"Unable to find training");
        }
    }



}
