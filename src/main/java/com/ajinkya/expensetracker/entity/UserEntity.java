package com.ajinkya.expensetracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Table(name="user", schema="expense_tracker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name="customer_id")
    @SequenceGenerator(name="customer_id_seq", sequenceName="expense_tracker.customer_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_id_seq")
    private long customerId;
    @Column(name="insert_dt")
    @Basic
    private Timestamp insertDt;
    @Column(name="customer_name")
    private String customerName;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="age")
    private int age;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String psswrd;
}
