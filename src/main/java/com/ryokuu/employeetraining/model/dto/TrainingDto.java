package com.ryokuu.employeetraining.model.dto;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryokuu.employeetraining.model.validation.InsertValidationGroup;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp deletedAt;

    @NotNull(message = "Id is mandatory", groups = {UpdateValidationGroup.class, InsertValidationGroup.class})
    private Integer id;

    private String trainer;

    private String topic;

}
