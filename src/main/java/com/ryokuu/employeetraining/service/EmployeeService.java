package com.ryokuu.employeetraining.service;

import com.ryokuu.employeetraining.model.dto.EmployeeDto;
import com.ryokuu.employeetraining.model.dto.GenericResponse;

 public interface EmployeeService {
     GenericResponse<Object> insertEmployee(EmployeeDto employeeDto);
     GenericResponse<Object> updateEmployee(EmployeeDto employeeDto);

    GenericResponse<Object> deleteEmployee(Integer id);

    GenericResponse<Object> getEmployee(Integer id);

    GenericResponse<Object> getEmployeeList(Integer page, Integer size);

}
