package com.ryokuu.employeetraining.repository;

import com.ryokuu.employeetraining.model.entity.Employee;
import com.ryokuu.employeetraining.repository.projection.GetEmployeeName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    GetEmployeeName findNameById(Integer id);

}
