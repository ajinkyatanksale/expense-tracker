package com.ajinkya.expensetracker.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @Size(min=1, max=20, message = "The length of full name must be between 2 and 100 characters.")
    private String userName;
    @NotNull
    @Size(min=6, max=20)
    private String password;
}
