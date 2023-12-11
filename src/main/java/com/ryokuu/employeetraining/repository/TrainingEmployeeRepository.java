package com.ryokuu.employeetraining.repository;

import com.ryokuu.employeetraining.model.entity.TrainingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, Integer>{
}
