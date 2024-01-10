package com.ryokuu.employeetraining.repository;

import com.ryokuu.employeetraining.model.entity.TrainingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, Integer>{

    @Modifying
    @Query("update TrainingEmployee te set te.employee.id = :employeeId, te.training.id = :trainingId where te.id = :id")
    void setEmployeeAndTrainingIds(@Param("id") Integer id, @Param("employeeId") Integer employeeId, @Param("trainingId") Integer trainingId);

    @Modifying
    @Query("update TrainingEmployee te set te.employee.id = :employeeId, te.training.id = :trainingId, te.dateOfTraining = :dateOfTraining where te.id = :id")
    void updateTrainingEmployee(@Param("id") Integer id, @Param("employeeId") Integer employeeId, @Param("trainingId") Integer trainingId, @Param("dateOfTraining") LocalDateTime dateOfTraining);

}
