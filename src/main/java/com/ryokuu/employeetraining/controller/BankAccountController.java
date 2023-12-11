package com.ryokuu.employeetraining.controller;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.ryokuu.employeetraining.model.dto.BankAccountDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import com.ryokuu.employeetraining.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/idstar/bankAccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    @PostMapping("/save")
    ResponseEntity<GenericResponse<Object>> saveBankAccount(@Valid @RequestBody BankAccountDto bankAccountDto) {

        GenericResponse<Object> response = bankAccountService.saveBankAccount(bankAccountDto);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/update")
    ResponseEntity<GenericResponse<Object>> updateBankAccount(@RequestBody @Validated(UpdateValidationGroup.class) BankAccountDto bankAccountDto) {

        GenericResponse<Object> response = bankAccountService.updateBankAccount(bankAccountDto);
        HttpStatus httpStatus = response.getCode() == null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }


    @GetMapping("/{id}")
    ResponseEntity<GenericResponse<Object>> getBankAccount(@PathVariable Integer id) {
        GenericResponse<Object> response = bankAccountService.getBankAccount(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<GenericResponse<Object>> deleteBankAccount(@PathVariable Integer id) {

        GenericResponse<Object> response = bankAccountService.deleteBankAccount(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/list")
    ResponseEntity<GenericResponse<Object>> getBankList(@RequestParam(required = false) Integer pageNumber,
                                                        @RequestParam(required = false) Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        GenericResponse<Object> response = bankAccountService.getBankAccountList(pageNumber, pageSize);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

}
