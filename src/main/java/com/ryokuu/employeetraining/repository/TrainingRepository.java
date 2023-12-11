package com.ryokuu.employeetraining.repository;

import com.ryokuu.employeetraining.model.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Integer>{
}
