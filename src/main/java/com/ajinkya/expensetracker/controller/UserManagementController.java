package com.ajinkya.expensetracker.controller;
import com.ajinkya.expensetracker.auth.JwtHelper;
import com.ajinkya.expensetracker.dto.auth.*;
import com.ajinkya.expensetracker.service.UserManagementService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserManagementController {
    @Autowired
    UserManagementService userManagementService;

    @Autowired
    private JwtHelper helper;

    @PostMapping("/enroll")
    public ResponseEntity<EnrollResponse> enrollUser(@Valid @RequestBody EnrollRequest enrollRequest, HttpSession session) {
        EnrollResponse enrollResponse = userManagementService.enrollUser(enrollRequest);
        session.setAttribute("name", enrollRequest.getName());
        session.setAttribute("username", enrollRequest.getUserName());
        session.setAttribute("age", enrollRequest.getAge());
        session.setAttribute("phoneNumber", enrollRequest.getPhoneNumber());
        return new ResponseEntity<>(enrollResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
        LoginResponse loginResponse = userManagementService.loginUser(loginRequest);

        if (loginResponse.isLoginStatus()) {
            UserInfo userInfo = userManagementService.getUserInfo(loginRequest.getUserName());
            String token = this.helper.generateToken(userInfo);
            loginResponse.setToken(token);
            session.setAttribute("name", userInfo.getName());
            session.setAttribute("username", userInfo.getUsername());
            session.setAttribute("age", userInfo.getAge());
            session.setAttribute("phoneNumber", userInfo.getPhoneNumber());
        }
        return new ResponseEntity<>(loginResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/otp")
    public String verifyOtp(@RequestBody OtpRequest otpRequest) {
        return "";
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        return new ResponseEntity<String>("User successfully logged out.", new HttpHeaders(), HttpStatus.OK);
    }
}
