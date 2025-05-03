package com.ajinkya.expensetracker.dto.auth;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EnrollRequest {
    @NotNull
    @Size(min=1, max=20, message = "The length of full name must be between 2 and 100 characters.")
    private String userName;
    @NotNull
    private String password;
    @NotNull
    @Size(min=1, max=40, message = "The length of full name must be between 2 and 40 characters.")
    private String name;
    @NotNull
    @Size(min=10, max=10)
    private String phoneNumber;
    @Positive
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
