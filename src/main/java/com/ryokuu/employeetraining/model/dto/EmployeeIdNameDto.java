package com.ryokuu.employeetraining.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIdNameDto {

    @NotNull
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;

}
