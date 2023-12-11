package com.ryokuu.employeetraining.model.entity;

/*
 * @author Muhammad Nooryoku Rafshanjany
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "bank_account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankAccount {

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private Timestamp deletedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accountNumber;

    private String accountName;

    private String bankName;

    private String branchName;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
