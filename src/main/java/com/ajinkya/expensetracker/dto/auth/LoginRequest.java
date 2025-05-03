package com.ajinkya.expensetracker.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotNull
    @Size(min=1, max=20, message = "The length of full name must be between 2 and 100 characters.")
    private String userName;
    @NotNull
    @Size(min=6, max=20)
    private String password;

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
}
