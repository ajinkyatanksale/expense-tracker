package com.ajinkya.expensetracker.dto.auth;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
