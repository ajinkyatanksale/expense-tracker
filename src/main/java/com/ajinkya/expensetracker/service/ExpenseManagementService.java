package com.ajinkya.expensetracker.service;

import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.ExpenseResponse;
import com.ajinkya.expensetracker.util.enums.Category;
import com.ajinkya.expensetracker.util.exception.FailureException;
import org.springframework.stereotype.Service;


@Service
public interface ExpenseManagementService {
    public GetAllExpenseResponse getAllExpenses(long customerId) throws FailureException;
    public ExpenseResponse addNewExpense(NewExpenseRequest newExpenseRequest, long customerId) throws FailureException;
    public GetAllExpenseResponse getAllExpensesByTitle(long customerId, String title) throws FailureException;
    public GetAllExpenseResponse getAllExpensesByCategory(long customerId, Category category) throws FailureException;
    public GetAllExpenseResponse getAllExpensesByDate(long customerId, String date) throws FailureException;
    public ExpenseResponse deleteExpenseById(long expenseId) throws FailureException;
    public ExpenseResponse updateExpenseById(long expenseId, long amount) throws FailureException;
}
