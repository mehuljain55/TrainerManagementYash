package com.yash.HrManager.service;

import com.yash.HrManager.Entity.DailySchedule;
import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.WeeklySchedule;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.TrainingStatus;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.repository.DailyScheduleRepo;
import com.yash.HrManager.repository.TraniningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private TraniningRepo traniningRepo;

    @Autowired
    private WeeklyScheduleService weeklyScheduleService;

    @Autowired
    private DailyScheduleRepo dailyScheduleRepo;

    public ApiResponseModel addNewTraining(User user,Training trainingRequest)
    {
        try {
          Training training= traniningRepo.save(trainingRequest);
          List<WeeklySchedule> weeklyScheduleList=weeklyScheduleService.generateWeeklySchedule(trainingRequest.getStartDate(),trainingRequest.getEndDate());
          List<DailySchedule> dailySchedules=generateDailySchedule(weeklyScheduleList,user,training);
            System.out.println(dailySchedules);
          trainingRequest.setStatus(TrainingStatus.PLANNED);
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
    public ApiResponseModel<List<DailySchedule>> findDailyScheduleByWeekIdAndTrainingId(Date startDate,Date endDate, int trainingId) {
      List<WeeklySchedule> weeklyScheduleList=weeklyScheduleService.getWeekByDates(startDate,endDate);
      List<DailySchedule> dailyScheduleList=new ArrayList<>();
      for(WeeklySchedule weeklySchedule:weeklyScheduleList)
      {
          List<DailySchedule> dailySchedules=dailyScheduleRepo.findDailyScheduleByWeekIdAndTrainingId(weeklySchedule.getWeekId(),trainingId);
          dailyScheduleList.addAll(dailySchedules);
      }
        System.out.println("Size:"+dailyScheduleList.size());
        return new ApiResponseModel<>(StatusResponse.success,dailyScheduleList,"Training Found");
    }

    public List<Date> generateDatesBetween(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        // Loop through all dates starting from the startDate to the endDate
        while (!calendar.getTime().after(endDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            // Only add weekdays (Monday to Friday)
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                dates.add(calendar.getTime());
                System.out.println("Adding: " + calendar.getTime()); // Debugging output
            }

            // Move to the next day
            calendar.add(Calendar.DATE, 1);
        }

        return dates;
    }

    List<DailySchedule> generateDailySchedule(List<WeeklySchedule> weeklyScheduleList,User user,Training training)
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
                dailySchedules.add(dailySchedule);
            }
        }
        return dailySchedules;
    }

}
