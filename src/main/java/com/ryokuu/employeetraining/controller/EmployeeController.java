package com.ryokuu.employeetraining.controller;

import com.ryokuu.employeetraining.model.dto.EmployeeDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import com.ryokuu.employeetraining.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/idstar/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;
    @PostMapping("/save")
    ResponseEntity<GenericResponse<Object>> postEmployee(@RequestBody @Valid EmployeeDto employeeDto) {

        log.info("masuk sini ->{}", employeeDto);

        GenericResponse<Object> response = employeeService.insertEmployee(employeeDto);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/update")
    ResponseEntity<GenericResponse<Object>> putEmployee(@RequestBody @Validated(UpdateValidationGroup.class) EmployeeDto employeeDto) {
        GenericResponse<Object> response = employeeService.updateEmployee(employeeDto);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/{id}")
    ResponseEntity<GenericResponse<Object>> getEmployee(@PathVariable Integer id) {
        GenericResponse<Object> response = employeeService.getEmployee(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<GenericResponse<Object>> deleteEmployee(@PathVariable Integer id) {

        GenericResponse<Object> response = employeeService.deleteEmployee(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/list")
    ResponseEntity<GenericResponse<Object>> getEmployee(@RequestParam(required = false) Integer pageNumber,
                                                        @RequestParam(required = false) Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        GenericResponse<Object> response = employeeService.getEmployeeList(pageNumber, pageSize);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }


}
