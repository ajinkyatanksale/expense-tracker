package com.ajinkya.expensetracker.service;

import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.NewExpenseResponse;
import com.ajinkya.expensetracker.util.enums.Category;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface ExpenseManagementService {
    public GetAllExpenseResponse getAllExpenses(long customerId);
    public NewExpenseResponse addNewExpense(NewExpenseRequest newExpenseRequest, long customerId);
    public GetAllExpenseResponse getAllExpensesByTitle(long customerId, String title);
    public GetAllExpenseResponse getAllExpensesByCategory(long customerId, Category category);
    public GetAllExpenseResponse getAllExpensesByDate(long customerId, String date);
    public NewExpenseResponse deleteExpenseById(long expenseId);
    public NewExpenseResponse updateExpenseById(long expenseId, long amount);
}
