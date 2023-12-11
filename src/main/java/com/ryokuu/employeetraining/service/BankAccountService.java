package com.ryokuu.employeetraining.service;

import com.ryokuu.employeetraining.model.dto.BankAccountDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
public interface BankAccountService {

    GenericResponse<Object> saveBankAccount(BankAccountDto bankAccountDto);

    GenericResponse<Object> updateBankAccount(BankAccountDto bankAccountDto);

    GenericResponse<Object> getBankAccount(Integer id);

    GenericResponse<Object> deleteBankAccount(Integer id);

    GenericResponse<Object> getBankAccountList(Integer pageNumber, Integer pageSize);

}
