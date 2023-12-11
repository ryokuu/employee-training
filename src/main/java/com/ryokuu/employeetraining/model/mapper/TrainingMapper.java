package com.ryokuu.employeetraining.model.mapper;

import com.ryokuu.employeetraining.model.dto.BankAccountDto;
import com.ryokuu.employeetraining.model.dto.TrainingDto;
import com.ryokuu.employeetraining.model.entity.BankAccount;
import com.ryokuu.employeetraining.model.entity.Training;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TrainingMapper {

    TrainingDto trainingToTrainingDto(Training training);

    @Mapping(target = "id", ignore = true)
    Training trainingToTrainingDto(TrainingDto trainingDto);

}
