package com.ryokuu.employeetraining.controller;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.dto.TrainingEmployeeDto;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import com.ryokuu.employeetraining.service.TrainingEmployeeService;
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
@RequestMapping("/v1/idstar/training-employee")
public class TrainingEmployeeController {

    private final TrainingEmployeeService trainingEmployeeService;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    @PostMapping("/save")
    ResponseEntity<GenericResponse<Object>> saveTraining(@Valid @RequestBody TrainingEmployeeDto trainingDto) {

        GenericResponse<Object> response = trainingEmployeeService.saveTrainingEmployee(trainingDto);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/update")
    ResponseEntity<GenericResponse<Object>> updateTrainingEmployee(@RequestBody @Validated(UpdateValidationGroup.class) TrainingEmployeeDto trainingDto) {

        GenericResponse<Object> response = trainingEmployeeService.updateTrainingEmployee(trainingDto);
        HttpStatus httpStatus = response.getCode() == null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }


    @GetMapping("/{id}")
    ResponseEntity<GenericResponse<Object>> getTrainingEmployee(@PathVariable Integer id) {
        GenericResponse<Object> response = trainingEmployeeService.getTrainingEmployee(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<GenericResponse<Object>> deleteTrainingEmployee(@PathVariable Integer id) {

        GenericResponse<Object> response = trainingEmployeeService.deleteTrainingEmployee(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/list")
    ResponseEntity<GenericResponse<Object>> getTrainingEmployeeList(@RequestParam(required = false) Integer pageNumber,
                                                        @RequestParam(required = false) Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        GenericResponse<Object> response = trainingEmployeeService.getTrainingEmployeeList(pageNumber, pageSize);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

}
