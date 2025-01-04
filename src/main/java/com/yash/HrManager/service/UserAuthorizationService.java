package com.yash.HrManager.service;

import com.yash.HrManager.Entity.User;
import com.yash.HrManager.Entity.enums.StatusResponse;
import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.enums.UserStatus;
import com.yash.HrManager.Entity.models.ApiResponseModel;
import com.yash.HrManager.Entity.models.UserLoginModel;
import com.yash.HrManager.repository.UserRepo;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthorizationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    public ApiResponseModel addUser(User user)
    {
        try {
            user.setRole(UserRoles.trainer);
            user.setStatus(UserStatus.active);
            user.setPassword(hashPassword(user.getPassword()));
            userRepo.save(user);
            return new ApiResponseModel(StatusResponse.success,null ,"Trainer Added");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ApiResponseModel(StatusResponse.failed,null ,"Unable to add trainer");
        }
    }


    public ApiResponseModel<UserLoginModel> validateUserLogin(String emailId, String password)
    {
        Optional<User> opt= userRepo.findById(emailId);
        if(opt.isPresent())
        {
            User user=opt.get();
            if(!(user.getStatus().equals(UserStatus.active)))
            {
                return  new ApiResponseModel<>(StatusResponse.unauthorized,null,"User approval pending");
            }
            else if((verifyPassword(password,user.getPassword())))
            {
                String token=jwtUtils.generateToken(user);
                UserLoginModel userLoginModel=new UserLoginModel(user,token);
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
        Optional<User> opt= userRepo.findById(emailId);
        if(opt.isPresent())
        {
            User user=opt.get();
            boolean status=jwtUtils.validateTokenForUser(user,token);
            return  status;
        } else {
            return  false;
        }
    }

    public boolean validateUserAccess(User user, String token, UserRoles requestedAccessRole)
    {
        Optional<User> opt= userRepo.findById(user.getEmailId());
        if(opt.isPresent())
        {
            User validateUser=opt.get();
            boolean status=jwtUtils.validateTokenForUserRole(validateUser,token,requestedAccessRole);
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
