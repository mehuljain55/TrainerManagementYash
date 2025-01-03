package com.yash.HrManager.service;

import com.yash.HrManager.Entity.Trainer;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.repository.TrainerRepo;
import com.yash.HrManager.repository.TraniningRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthorizationService {

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private JwtUtils jwtUtils;

    public boolean validateUserToken(String emailId,String token)
    {
        Optional<Trainer> opt=trainerRepo.findById(emailId);
        if(opt.isPresent())
        {
            Trainer user=opt.get();
            boolean status=jwtUtils.validateTokenForUser(user,token);
            return  status;
        } else {
            return  false;
        }
    }

    public boolean validateUserAccess(Trainer trainer, String token, UserRoles requestedAccessRole)
    {
        Optional<Trainer> opt=trainerRepo.findById(trainer.getEmailId());
        if(opt.isPresent())
        {
            Trainer validateTrainer=opt.get();
            boolean status=jwtUtils.validateTokenForUserRole(validateTrainer,token,requestedAccessRole);
            return  status;
        } else {
            return  false;
        }
    }

    private String hashPassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }

}
