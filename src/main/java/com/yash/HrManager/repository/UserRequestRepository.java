package com.yash.HrManager.repository;


import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.UserRequests;
import com.yash.HrManager.Entity.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserRequestRepository extends JpaRepository<UserRequests,Integer> {

    @Query("SELECT u FROM UserRequests u WHERE u.trainingId = :trainingId AND u.dailyScheduledId=:dailyScheduledId AND u.validTill>=:currentDate AND t.status = :status")
    UserRequests findUserEditRequest(@Param("trainingId") int trainingId,
                                                 @Param("dailyScheduledId") int dailyScheduledId,
                                                 @Param("currentDate") Date currentDate,
                                                 @Param("status") RequestStatus status);

}
