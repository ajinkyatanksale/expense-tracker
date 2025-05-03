package com.ajinkya.expensetracker.service;

import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.NewExpenseResponse;
import com.ajinkya.expensetracker.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseManagementService {

    public GetAllExpenseResponse getAllExpenses(long customerId);
    public NewExpenseResponse addNewExpense(NewExpenseRequest newExpenseRequest, long customerId);
}
