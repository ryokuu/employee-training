package com.ryokuu.employeetraining.service;

import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.dto.TrainingDto;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
public interface TrainingService {

    GenericResponse<Object> saveTraining(TrainingDto trainingDto);

    GenericResponse<Object> updateTraining(TrainingDto trainingDto);

    GenericResponse<Object> getTraining(Integer id);

    GenericResponse<Object> deleteTraining(Integer id);

    GenericResponse<Object> getTrainingList(Integer pageNumber, Integer pageSize);

}
