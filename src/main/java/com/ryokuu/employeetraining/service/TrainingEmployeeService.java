package com.ryokuu.employeetraining.service;

import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.dto.TrainingEmployeeDto;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
public interface TrainingEmployeeService {

    GenericResponse<Object> saveTrainingEmployee(TrainingEmployeeDto trainingDto);

    GenericResponse<Object> updateTrainingEmployee(TrainingEmployeeDto trainingDto);

    GenericResponse<Object> getTrainingEmployee(Integer id);

    GenericResponse<Object> deleteTrainingEmployee(Integer id);

    GenericResponse<Object> getTrainingEmployeeList(Integer pageNumber, Integer pageSize);

}
