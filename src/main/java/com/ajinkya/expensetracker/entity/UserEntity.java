package com.ajinkya.expensetracker.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="user", schema="expense_tracker")
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsswrd() {
        return psswrd;
    }

    public void setPsswrd(String psswrd) {
        this.psswrd = psswrd;
    }

    public Timestamp getInsertDt() {
        return insertDt;
    }

    public void setInsertDt(Timestamp insertDt) {
        this.insertDt = insertDt;
    }
}
