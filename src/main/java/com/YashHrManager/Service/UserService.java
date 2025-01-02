package com.YashHrManager.Service;

import com.YashHrManager.Entity.Trainer;
import com.YashHrManager.Repository.TrainerRepo;
import com.YashHrManager.Repository.TraniningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private TraniningRepo traniningRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    public String addTrainer(Trainer trainer)
    {
        try {
            trainerRepo.save(trainer);
            return "Success";

        }catch (Exception e)
        {
            e.printStackTrace();
            return "Failed";
        }
    }

}
