package com.ryokuu.employeetraining.model.mapper;

import com.ryokuu.employeetraining.model.dto.BankAccountDto;
import com.ryokuu.employeetraining.model.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BankAccountMapper {

    BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount);

    @Mapping(target = "id", ignore = true)
    BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto);

}
