package com.ryokuu.employeetraining.model.mapper;

import com.ryokuu.employeetraining.model.dto.TrainingDto;
import com.ryokuu.employeetraining.model.dto.TrainingEmployeeDto;
import com.ryokuu.employeetraining.model.entity.Training;
import com.ryokuu.employeetraining.model.entity.TrainingEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TrainingEmployeeMapper {

    TrainingEmployeeDto trainingToTrainingDto(TrainingEmployee training);


}
