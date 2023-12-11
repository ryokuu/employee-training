package com.ryokuu.employeetraining.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse <T>{

    private Integer code;

    private T data;

    private String status;

    private String message;

    public void success(T data) {

        this.code = 200;
        this.data = data;
        this.status = "success";

    }

    public GenericResponse<T> error(String message) {
        return new GenericResponse<>(500, null, "error", message);
    }

    public GenericResponse<T> notFound(String message) {
        return new GenericResponse<>(404, null, "not found", message);
    }

    public GenericResponse<T> badRequest(String message) {
        return new GenericResponse<>(400, null, "bad request", message);
    }

    public GenericResponse<T> unauthorized(String message) {
        return new GenericResponse<>(401, null, "unauthorized", message);
    }

    public GenericResponse<T> forbidden(String message) {
        return new GenericResponse<>(403, null, "forbidden", message);
    }

    public GenericResponse<T> conflict(String message) {
        return new GenericResponse<>(409, null, "conflict", message);
    }

}
