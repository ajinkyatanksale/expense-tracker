package com.ajinkya.expensetracker.controller;

import com.ajinkya.expensetracker.auth.JwtAuthenticationFilter;
import com.ajinkya.expensetracker.auth.JwtHelper;
import com.ajinkya.expensetracker.dao.UserManagementDao;
import com.ajinkya.expensetracker.dto.auth.UserInfo;
import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.NewExpenseResponse;
import com.ajinkya.expensetracker.service.ExpenseManagementService;
import com.ajinkya.expensetracker.service.UserManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseManagementService expenseManagementService;

    @Autowired
    JwtAuthenticationFilter jwtFilter;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    UserManagementService userManagementService;

    @GetMapping("/getAllExpenses")
    public GetAllExpenseResponse getAllExpenses() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        return expenseManagementService.getAllExpenses(userInfo.getCustomerId());
    }

    @PostMapping("/addNewExpense")
    public NewExpenseResponse addNewExpense(@Valid @RequestBody NewExpenseRequest newExpenseRequest) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        return expenseManagementService.addNewExpense(newExpenseRequest, userInfo.getCustomerId());
    }
}
