package com.ryokuu.employeetraining.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingEmployeeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp deletedAt;

    @NotNull(message = "Id is mandatory", groups = UpdateValidationGroup.class)
    private Integer id;

    private LocalDateTime dateOfTraining;

    private EmployeeDto employee;

    private TrainingDto training;


}
