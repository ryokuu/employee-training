package com.ryokuu.employeetraining.repository;

import com.ryokuu.employeetraining.model.entity.BankAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    @Transactional
    @Modifying
    @Query("update BankAccount ba set ba.employee.id = :employeeId where ba.id = :id")
    void updateSetEmployeeId(@Param("id") Integer id, @Param("employeeId") Integer employeeId);

}
