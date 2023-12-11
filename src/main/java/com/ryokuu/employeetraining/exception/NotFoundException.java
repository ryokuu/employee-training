package com.ryokuu.employeetraining.exception;

import com.ryokuu.employeetraining.model.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends CommonException{

    public static final String CODE = "Failed";
    public static final String TYPE = "BadRequest";

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, CODE, TYPE, message);
    }

}
