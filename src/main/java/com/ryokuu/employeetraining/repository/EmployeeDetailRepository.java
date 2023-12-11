package com.ryokuu.employeetraining.repository;

import com.ryokuu.employeetraining.model.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Integer> {

    @Modifying
    @Query("update EmployeeDetail ed set ed.employee.id = :employeeId where ed.id = :id")
    void setEmployeeId(@Param("id") Integer id, @Param("employeeId") Integer employeeId);

}
