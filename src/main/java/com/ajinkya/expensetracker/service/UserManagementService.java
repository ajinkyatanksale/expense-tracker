package com.ajinkya.expensetracker.service;

import com.ajinkya.expensetracker.dto.auth.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserManagementService extends UserDetailsService {

    EnrollResponse enrollUser(EnrollRequest enrollRequest);

    LoginResponse loginUser(LoginRequest loginRequest);

    UserInfo getUserInfo (String userName);
}
