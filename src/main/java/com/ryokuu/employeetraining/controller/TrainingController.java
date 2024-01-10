package com.ryokuu.employeetraining.controller;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import com.ryokuu.employeetraining.model.dto.BankAccountDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;
import com.ryokuu.employeetraining.model.dto.TrainingDto;
import com.ryokuu.employeetraining.model.validation.UpdateValidationGroup;
import com.ryokuu.employeetraining.service.BankAccountService;
import com.ryokuu.employeetraining.service.TrainingService;
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
@RequestMapping("/v1/idstar/training")
public class TrainingController {

    private final TrainingService trainingService;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    @PostMapping("/save")
    ResponseEntity<GenericResponse<Object>> saveTraining(@Valid @RequestBody TrainingDto trainingDto) {

        GenericResponse<Object> response = trainingService.saveTraining(trainingDto);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/update")
    ResponseEntity<GenericResponse<Object>> updateTraining(@RequestBody @Validated(UpdateValidationGroup.class) TrainingDto trainingDto) {

        GenericResponse<Object> response = trainingService.updateTraining(trainingDto);
        HttpStatus httpStatus = response.getCode() == null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }


    @GetMapping("/{id}")
    ResponseEntity<GenericResponse<Object>> getTraining(@PathVariable Integer id) {
        GenericResponse<Object> response = trainingService.getTraining(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<GenericResponse<Object>> deleteTraining(@PathVariable Integer id) {

        GenericResponse<Object> response = trainingService.deleteTraining(id);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/list")
    ResponseEntity<GenericResponse<Object>> getTrainingList(@RequestParam(required = false) Integer pageNumber,
                                                        @RequestParam(required = false) Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        GenericResponse<Object> response = trainingService.getTrainingList(pageNumber, pageSize);
        HttpStatus httpStatus = response.getCode()==null ?
                HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(response.getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

}
