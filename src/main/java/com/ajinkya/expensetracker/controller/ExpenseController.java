package com.ajinkya.expensetracker.controller;

import com.ajinkya.expensetracker.auth.JwtAuthenticationFilter;
import com.ajinkya.expensetracker.auth.JwtHelper;
import com.ajinkya.expensetracker.dao.UserManagementDao;
import com.ajinkya.expensetracker.dto.auth.UserInfo;
import com.ajinkya.expensetracker.dto.expense.ExpenseInfo;
import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.NewExpenseResponse;
import com.ajinkya.expensetracker.service.ExpenseManagementService;
import com.ajinkya.expensetracker.service.UserManagementService;
import com.ajinkya.expensetracker.util.enums.Category;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;


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

    @GetMapping("/getExpenseByTitle")
    public GetAllExpenseResponse getExpensesByTitle(@RequestParam("title") String title) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        return expenseManagementService.getAllExpensesByTitle(userInfo.getCustomerId(), title);
    }

    @GetMapping("/getExpenseByCategory")
    public GetAllExpenseResponse getExpensesByCategory(@RequestParam("category") Category category) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        return expenseManagementService.getAllExpensesByCategory(userInfo.getCustomerId(), category);
    }

    @GetMapping("/getExpenseByDate")
    public GetAllExpenseResponse getExpenseByDate(@RequestParam("date") String date) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        return expenseManagementService.getAllExpensesByDate(userInfo.getCustomerId(), date);
    }

    @DeleteMapping("/delete/{expenseId}")
    public NewExpenseResponse deleteExpense(@PathVariable String expenseId) {
        return expenseManagementService.deleteExpenseById(Long.parseLong(expenseId));
    }

    @PutMapping("/update/{expenseId}")
    public NewExpenseResponse updateExpense(@PathVariable String expenseId, @RequestParam("amount") String amount) {
        return expenseManagementService.updateExpenseById(Long.parseLong(expenseId), Long.parseLong(amount));
    }
}
