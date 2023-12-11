package com.ryokuu.employeetraining.exception;

import com.ryokuu.employeetraining.model.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        // Log the validation errors
        log.error("Validation failed: {}", fieldErrors);

        StringBuilder error = new StringBuilder();
        fieldErrors.forEach(fieldError -> {
            error.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage());
        });

        return new ResponseEntity<>(new GenericResponse<>().badRequest(error.toString()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericResponse<Object>> handleNotFoundException(NotFoundException e) {
        log.error("An error occurred:", e);
        return new ResponseEntity<>(new GenericResponse<>().notFound(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GenericResponse<Object>> handleException(Exception e) {
        log.error("An error occurred:", e);
        return new ResponseEntity<>(new GenericResponse<>().error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
