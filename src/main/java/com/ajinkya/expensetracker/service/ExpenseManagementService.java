package com.ajinkya.expensetracker.service;

import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.ExpenseResponse;
import com.ajinkya.expensetracker.util.enums.Category;
import org.springframework.stereotype.Service;


@Service
public interface ExpenseManagementService {
    public GetAllExpenseResponse getAllExpenses(long customerId);
    public ExpenseResponse addNewExpense(NewExpenseRequest newExpenseRequest, long customerId);
    public GetAllExpenseResponse getAllExpensesByTitle(long customerId, String title);
    public GetAllExpenseResponse getAllExpensesByCategory(long customerId, Category category);
    public GetAllExpenseResponse getAllExpensesByDate(long customerId, String date);
    public ExpenseResponse deleteExpenseById(long expenseId);
    public ExpenseResponse updateExpenseById(long expenseId, long amount);
}
