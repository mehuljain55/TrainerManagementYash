package com.yash.HrManager.service;

import com.yash.HrManager.Entity.*;
import com.yash.HrManager.Entity.enums.*;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.repository.DailyScheduleRepo;
import com.yash.HrManager.repository.TraniningRepo;
import com.yash.HrManager.repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TrainingService {

    @Autowired
    private TraniningRepo traniningRepo;

    @Autowired
    private WeeklyScheduleService weeklyScheduleService;

    @Autowired
    private DailyScheduleRepo dailyScheduleRepo;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private UserRequestRepository userRequestRepository;


    public ApiResponseModel addNewTraining(User user, Training trainingRequest, MultipartFile file)
    {
        try {
          Training training= traniningRepo.save(trainingRequest);
          List<WeeklySchedule> weeklyScheduleList=weeklyScheduleService.generateWeeklySchedule(trainingRequest.getStartDate(),trainingRequest.getEndDate());
          List<DailySchedule> dailySchedules=generateTrainingDailySchedule(weeklyScheduleList,user,training);
          String filepath=fileUploadService.setFilePath(file,training);
          if(!filepath.equals("Failed"))
          {
              trainingRequest.setFilePath(filepath);
          }
          trainingRequest.setStatus(TrainingStatus.PENDING);
          trainingRequest.setEmailId(user.getEmailId());
          trainingRequest.setTrainerName(user.getName());
          trainingRequest.setWeeklySchedules(weeklyScheduleList);
          traniningRepo.save(trainingRequest);
          dailyScheduleRepo.saveAll(dailySchedules);

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
    public ApiResponseModel<List<DailySchedule>> findDailyScheduleByWeekIdAndTrainingId(int trainingId) {
        Optional<Training> optionalTraining=traniningRepo.findById(trainingId);

        if(optionalTraining.isPresent()) {
            Training training=optionalTraining.get();
            List<WeeklySchedule> weeklyScheduleList = weeklyScheduleService.getWeekByDates(training.getStartDate(), training.getEndDate());
            List<DailySchedule> dailyScheduleList = new ArrayList<>();
            for (WeeklySchedule weeklySchedule : weeklyScheduleList) {
                List<DailySchedule> dailySchedules = dailyScheduleRepo.findDailyScheduleByWeekIdANDTrainingType(weeklySchedule.getWeekId(), trainingId,TrainingType.TRAINING);
                  for(DailySchedule schedule:dailySchedules)
                  {
                   UserRequests userRequests=userRequestRepository.findUserEditRequest(trainingId,schedule.getSno(),new Date(),RequestStatus.approved);
                   if(userRequests!=null && userRequests.getDailyScheduledId()==schedule.getSno())
                   {
                       schedule.setModfiyStatus(ModfiyStatus.enabled);
                   }
                        schedule.setWeekScheduleId(weeklySchedule.getWeekId());
                        dailyScheduleList.add(schedule);
                  }
            }
            return new ApiResponseModel<>(StatusResponse.success, dailyScheduleList, "Training Found");
        }else {
            return new ApiResponseModel<>(StatusResponse.success, null, "Training not found");
        }
    }

    public ApiResponseModel updateTrainingStatus(List<Training> trainingList)
    {
        for(Training trainingDetail:trainingList)
        {
            Optional<Training> trainingOptional=traniningRepo.findById(trainingDetail.getTrainingId());
            if(trainingOptional.isPresent())
            {
                Training training=trainingOptional.get();
                training.setStatus(trainingDetail.getStatus());
                traniningRepo.save(training);
            }
        }
        return new ApiResponseModel(StatusResponse.success,null,"Training status updated");
    }

    public ApiResponseModel updateDailySchedule(List<DailySchedule> dailyScheduleList) {
        for(DailySchedule schedule:dailyScheduleList)
        {
            try {
                Optional<DailySchedule> dailyScheduleOptional=dailyScheduleRepo.findById(schedule.getSno());
                if(dailyScheduleOptional.isPresent())
                {
                    DailySchedule dailySchedule=dailyScheduleOptional.get();
                    if(schedule.getTrainerAttendance().equals(TrainerAttendance.LEAVE))
                    {
                        dailySchedule.setDescription("Trainer on leave");
                    }else {
                        dailySchedule.setDescription(schedule.getDescription());
                    }
                    dailySchedule.setTrainerAttendance(schedule.getTrainerAttendance());
                    dailyScheduleRepo.save(dailySchedule);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return new ApiResponseModel<>(StatusResponse.success,null,"Daily Schedule Updated");

    }

    public List<Date> generateDatesBetween(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                dates.add(calendar.getTime());
            }
            calendar.add(Calendar.DATE, 1);
        }

        return dates;
    }

    public List<DailySchedule> generateTrainingDailySchedule(List<WeeklySchedule> weeklyScheduleList,User user,Training training)
    {
        List<Date> dateList = generateDatesBetween(training.getStartDate(), training.getEndDate());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE"); // Formatter for day names
        List<DailySchedule> dailySchedules=new ArrayList<>();
        for (Date date : dateList) {
            WeeklySchedule matchingWeek = weeklyScheduleList.stream()
                    .filter(ws -> !date.before(ws.getWeekStartDate()) && !date.after(ws.getWeekEndDate()))
                    .findFirst()
                    .orElse(null);

            if (matchingWeek != null) {
                DailySchedule dailySchedule = new DailySchedule();
                dailySchedule.setDate(date);
                dailySchedule.setWeeklySchedule(matchingWeek);
                dailySchedule.setTrainingId(training.getTrainingId());
                dailySchedule.setDay(dayFormat.format(date)); // Set day name (e.g., Monday, Tuesday)
                dailySchedule.setEmailId(user.getEmailId());
                dailySchedule.setModfiyStatus(ModfiyStatus.disabled);
                dailySchedule.setType(TrainingType.TRAINING);
                dailySchedule.setTrainerAttendance(TrainerAttendance.PRESENT);
                dailySchedules.add(dailySchedule);
            }
        }
        return dailySchedules;
    }

    public ApiResponseModel deleteTrainingUserandTrainingId(int trainingId,User user)
    {
        try {
         Training training=traniningRepo.findTrainingsByEmailAndTrainingId(user.getEmailId(),trainingId);
         if(training!=null)
         {
             List<DailySchedule> dailyScheduleList=dailyScheduleRepo.findDailyScheduleByTrainingId(trainingId);
             dailyScheduleRepo.deleteAll(dailyScheduleList);
             traniningRepo.delete(training);
             return new ApiResponseModel(StatusResponse.success,null,"Training Deleted");
         }else {
             return new ApiResponseModel(StatusResponse.not_found,null,"Training not found");
         }
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel(StatusResponse.failed,null,"Unable to delete training");
        }
    }

}
