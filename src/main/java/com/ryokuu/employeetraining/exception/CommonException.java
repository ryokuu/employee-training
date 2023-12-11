package com.ryokuu.employeetraining.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends Exception{

    private final String code;
    private final String type;
    private final String displayMessage;

    private final HttpStatus httpStatus;

    public CommonException(HttpStatusCode status, String code, String type, String message) {
        super(message);
        this.httpStatus = HttpStatus.valueOf(status.value());
        this.code = code;
        this.type = type;
        this.displayMessage = message;
    }

}
