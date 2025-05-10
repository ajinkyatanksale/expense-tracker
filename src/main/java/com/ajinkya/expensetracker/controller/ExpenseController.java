package com.ajinkya.expensetracker.controller;

import com.ajinkya.expensetracker.auth.JwtAuthenticationFilter;
import com.ajinkya.expensetracker.auth.JwtHelper;
import com.ajinkya.expensetracker.dto.auth.UserInfo;
import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.ExpenseResponse;
import com.ajinkya.expensetracker.service.ExpenseManagementService;
import com.ajinkya.expensetracker.service.UserManagementService;
import com.ajinkya.expensetracker.util.enums.Category;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GetAllExpenseResponse> getAllExpenses() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        GetAllExpenseResponse getAllExpenseResponse = expenseManagementService.getAllExpenses(userInfo.getCustomerId());
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(getAllExpenseResponse, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/addNewExpense")
    public ResponseEntity<ExpenseResponse> addNewExpense(@Valid @RequestBody NewExpenseRequest newExpenseRequest) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        ExpenseResponse expenseResponse = expenseManagementService.addNewExpense(newExpenseRequest, userInfo.getCustomerId());
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(expenseResponse, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/getExpenseByTitle")
    public ResponseEntity<?> getExpensesByTitle(@RequestParam("title") String title) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        GetAllExpenseResponse getAllExpenseResponse = expenseManagementService.getAllExpensesByTitle(userInfo.getCustomerId(), title);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(getAllExpenseResponse, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/getExpenseByCategory")
    public ResponseEntity<GetAllExpenseResponse> getExpensesByCategory(@RequestParam("category") Category category) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        GetAllExpenseResponse getAllExpenseResponse = expenseManagementService.getAllExpensesByCategory(userInfo.getCustomerId(), category);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(getAllExpenseResponse, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/getExpenseByDate")
    public ResponseEntity<GetAllExpenseResponse> getExpenseByDate(@RequestParam("date") String date) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = jwtFilter.getTokenFromRequest(request);
        String username = jwtHelper.getUsernameFromToken(token);
        UserInfo userInfo = userManagementService.getUserInfo(username);
        GetAllExpenseResponse getAllExpenseResponse = expenseManagementService.getAllExpensesByDate(userInfo.getCustomerId(), date);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(getAllExpenseResponse, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<ExpenseResponse> deleteExpense(@PathVariable String expenseId) {
        ExpenseResponse expenseResponse = expenseManagementService.deleteExpenseById(Long.parseLong(expenseId));
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(expenseResponse, httpHeaders, HttpStatus.OK);
    }

    @PutMapping("/update/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable String expenseId, @RequestParam("amount") String amount) {
        ExpenseResponse expenseResponse = expenseManagementService.updateExpenseById(Long.parseLong(expenseId), Long.parseLong(amount));
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(expenseResponse, httpHeaders, HttpStatus.OK);
    }
}
