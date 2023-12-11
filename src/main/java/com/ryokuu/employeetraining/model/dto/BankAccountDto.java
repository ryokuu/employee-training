package com.ryokuu.employeetraining.model.dto;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp deletedAt;

    @NotNull(message = "Id is mandatory", groups = UpdateValidationGroup.class)
    private Integer id;

    @NotBlank
    private String accountName;

    @NotBlank
    private String accountNumber;

    private String bankName;

    private String branchName;

    @Valid
    private EmployeeIdNameDto employee;

}
