package com.yash.HrManager.service;

import com.yash.HrManager.Entity.Trainer;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.enums.UserStatus;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.Entity.models.UserLoginModel;
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

    public ApiResponseModel<UserLoginModel> validateUserLogin(String emailId, String password)
    {
        Optional<Trainer> opt=trainerRepo.findById(emailId);
        if(opt.isPresent())
        {
            Trainer trainer=opt.get();
            if(!(trainer.getStatus().equals(UserStatus.active)))
            {
                return  new ApiResponseModel<>(StatusResponse.unauthorized,null,"User approval pending");
            }
            else if((verifyPassword(password,trainer.getPassword())))
            {
                String token=jwtUtils.generateToken(trainer);
                UserLoginModel userLoginModel=new UserLoginModel(trainer,token);
                return  new ApiResponseModel<>(StatusResponse.success,userLoginModel,"User Validated");
            }else {
                return  new ApiResponseModel<>(StatusResponse.unauthorized,null,"Invalid password");
            }
        }else {
            return  new ApiResponseModel<>(StatusResponse.not_found,null,"User Not exists");
        }
    }

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
