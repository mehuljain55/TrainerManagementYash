package com.yash.HrManager.repository;

import com.yash.HrManager.Entity.Training;
import com.yash.HrManager.Entity.enums.TrainingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraniningRepo extends JpaRepository<Training,Integer> {

    @Query("SELECT t FROM Training t WHERE t.emailId = :emailId AND t.status = :status")
    List<Training> findTrainingsByEmailAndStatus(@Param("emailId") String emailId, @Param("status") TrainingStatus status);

    @Query("SELECT t FROM Training t WHERE t.status = :status")
    List<Training> findTrainingsByStatus(@Param("status") TrainingStatus status);


    @Query("SELECT t FROM Training t WHERE t.emailId = :emailId AND t.trainingId = :trainingId")
    Training findTrainingsByEmailAndTrainingId(@Param("emailId") String emailId, @Param("trainingId") int trainingId);

}
