package com.ryokuu.employeetraining.model.dto;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp deletedAt;

    @NotNull(message = "Id is mandatory", groups = UpdateValidationGroup.class)
    private Integer id;

    private String npwp;

    private String nik;

}
