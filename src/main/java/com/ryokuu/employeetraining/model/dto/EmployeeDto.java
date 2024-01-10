package com.ryokuu.employeetraining.model.dto;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryokuu.employeetraining.model.validation.InsertValidationGroup;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp deletedAt;

    @NotNull(message = "Id is mandatory", groups = {UpdateValidationGroup.class, InsertValidationGroup.class})
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotNull(message = "Date of birth is mandatory")
    private Date dateOfBirth;

    @Valid
    private EmployeeDetailDto employeeDetail;

}
