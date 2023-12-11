package com.ryokuu.employeetraining.model.mapper;

import com.ryokuu.employeetraining.model.dto.EmployeeDetailDto;
import com.ryokuu.employeetraining.model.dto.EmployeeDto;
import com.ryokuu.employeetraining.model.entity.Employee;
import com.ryokuu.employeetraining.model.entity.EmployeeDetail;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);

    @Mapping(target = "id", ignore = true)
    EmployeeDetail employeeDetailDtoToEmployeeDetail(EmployeeDetailDto employeeDetailDto);

    @Mapping(target = "id", ignore = true)
    void updateEmployeeFromEmployeeDto(EmployeeDto employeeDto, @MappingTarget Employee employee);

}
