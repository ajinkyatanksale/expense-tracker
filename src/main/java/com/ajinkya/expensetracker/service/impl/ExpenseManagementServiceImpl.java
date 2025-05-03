package com.ajinkya.expensetracker.service.impl;

import com.ajinkya.expensetracker.dao.ExpenseDao;
import com.ajinkya.expensetracker.dao.UserManagementDao;
import com.ajinkya.expensetracker.dto.expense.ExpenseInfo;
import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.NewExpenseResponse;
import com.ajinkya.expensetracker.entity.ExpenseEntity;
import com.ajinkya.expensetracker.entity.UserEntity;
import com.ajinkya.expensetracker.service.ExpenseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseManagementServiceImpl implements ExpenseManagementService {

    @Autowired
    ExpenseDao expenseDao;

    @Autowired
    UserManagementDao userManagementDao;

    @Override
    public GetAllExpenseResponse getAllExpenses(long customerId) {
        GetAllExpenseResponse getAllExpenseResponse = new GetAllExpenseResponse();
        Optional<UserEntity> user = userManagementDao.findById(customerId);
        if (user.isPresent()) {
            List<ExpenseEntity> expenseEntity = expenseDao.findExpenseByUser(user.get());
            List<ExpenseInfo> expenseInfoList = new ArrayList<>();
            for (ExpenseEntity e : expenseEntity) {
                ExpenseInfo expenseInfo = new ExpenseInfo();
                expenseInfo.setExpenseTitle(e.getExpenseTitle());
                expenseInfo.setAmount(e.getAmount());
                expenseInfo.setNote(e.getNote());
                expenseInfo.setInsert_dt(e.getInsertDate());
                expenseInfoList.add(expenseInfo);
            }
            getAllExpenseResponse.setExpenseInfos(expenseInfoList);
        }
        return getAllExpenseResponse;
    }

    @Override
    public NewExpenseResponse addNewExpense(NewExpenseRequest newExpenseRequest, long customerId) {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setExpenseTitle(newExpenseRequest.getExpenseTitle());
        expenseEntity.setAmount(newExpenseRequest.getAmount());
        expenseEntity.setNote(newExpenseRequest.getNote());
        expenseEntity.setInsertDate(new Timestamp(System.currentTimeMillis()));
        Optional<UserEntity> user = userManagementDao.findById(customerId);
        user.ifPresent(expenseEntity::setUser);
        ExpenseEntity expenseEntity1 = expenseDao.save(expenseEntity);
        NewExpenseResponse newExpenseResponse = new NewExpenseResponse();
        if (expenseEntity1.getExpenseId() > 0) {
            newExpenseResponse.setMessage("New Expense added successfully");
            newExpenseResponse.setRecordCreated(true);
        } else {
            newExpenseResponse.setMessage("Error occurred while adding new expense. Please try again!!");
            newExpenseResponse.setRecordCreated(false);
        }
        return newExpenseResponse;
    }
}
