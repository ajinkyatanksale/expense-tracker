package com.ajinkya.expensetracker.service.impl;

import com.ajinkya.expensetracker.dao.ExpenseDao;
import com.ajinkya.expensetracker.dao.UserManagementDao;
import com.ajinkya.expensetracker.dto.expense.ExpenseInfo;
import com.ajinkya.expensetracker.dto.expense.GetAllExpenseResponse;
import com.ajinkya.expensetracker.dto.expense.NewExpenseRequest;
import com.ajinkya.expensetracker.dto.expense.ExpenseResponse;
import com.ajinkya.expensetracker.entity.ExpenseEntity;
import com.ajinkya.expensetracker.entity.UserEntity;
import com.ajinkya.expensetracker.service.ExpenseManagementService;
import com.ajinkya.expensetracker.util.enums.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private final Logger logger = LoggerFactory.getLogger(ExpenseManagementServiceImpl.class);
    private final String LOG_ENTRY = "Entry";
    private final String LOG_EXIT = "EXIT";

    @Override
    public GetAllExpenseResponse getAllExpenses(long customerId) {
        String logPrefix = "getAllExpenses()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        GetAllExpenseResponse getAllExpenseResponse = new GetAllExpenseResponse();
        Optional<UserEntity> user = userManagementDao.findById(customerId);
        if (user.isPresent()) {
            logger.debug(logPrefix + "User found with customerId [" + user.get().getCustomerId() + "]");
            List<ExpenseEntity> expenseEntity = expenseDao.findExpenseByUser(user.get());
            List<ExpenseInfo> expenseInfoList = new ArrayList<>();
            for (ExpenseEntity e : expenseEntity) {
                ExpenseInfo expenseInfo = new ExpenseInfo();
                expenseInfo.setExpenseId(e.getExpenseId());
                expenseInfo.setExpenseTitle(e.getExpenseTitle());
                expenseInfo.setAmount(e.getAmount());
                expenseInfo.setNote(e.getNote());
                expenseInfo.setInsert_dt(e.getInsertDate());
                expenseInfo.setCategory(e.getCategory());
                expenseInfoList.add(expenseInfo);
            }
            getAllExpenseResponse.setExpenseInfos(expenseInfoList);
            logger.debug(logPrefix + expenseInfoList.size() + " Expenses retrieved for customerId [" + user.get().getCustomerId() + "]");
        }
        logger.debug(logPrefix + "[" + LOG_EXIT + "]");
        return getAllExpenseResponse;
    }

    @Override
    public ExpenseResponse addNewExpense(NewExpenseRequest newExpenseRequest, long customerId) {
        String logPrefix = "addNewExpense()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setExpenseTitle(newExpenseRequest.getExpenseTitle());
        expenseEntity.setAmount(newExpenseRequest.getAmount());
        expenseEntity.setNote(newExpenseRequest.getNote());
        expenseEntity.setInsertDate(new Timestamp(System.currentTimeMillis()));
        expenseEntity.setCategory(newExpenseRequest.getCategory());
        Optional<UserEntity> user = userManagementDao.findById(customerId);
        user.ifPresent(expenseEntity::setUser);
        ExpenseEntity expenseEntity1 = expenseDao.save(expenseEntity);
        ExpenseResponse expenseResponse = new ExpenseResponse();
        if (expenseEntity1.getExpenseId() > 0) {
            expenseResponse.setMessage("New Expense added successfully");
            expenseResponse.setRecordCreated(true);
            logger.debug(logPrefix + "New Expense added successfully with customerId [" + customerId + "]");
        } else {
            expenseResponse.setMessage("Error occurred while adding new expense. Please try again!!");
            expenseResponse.setRecordCreated(false);
            logger.error(logPrefix + "Error occurred while adding new expense for customerId [" + customerId + "]");
        }
        logger.debug(logPrefix + "[" + LOG_EXIT + "]");
        return expenseResponse;
    }

    @Override
    public GetAllExpenseResponse getAllExpensesByTitle(long customerId, String title) {
        String logPrefix = "getAllExpensesByTitle()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        GetAllExpenseResponse getAllExpenseResponse = new GetAllExpenseResponse();
        Optional<UserEntity> user = userManagementDao.findById(customerId);
        if (user.isPresent()) {
            logger.debug(logPrefix + "User found with customerId [" + user.get().getCustomerId() + "]");
            List<ExpenseEntity> expenseEntity = expenseDao.findExpenseByUserAndTitle(user.get(), title);
            List<ExpenseInfo> expenseInfoList = new ArrayList<>();
            for (ExpenseEntity e : expenseEntity) {
                ExpenseInfo expenseInfo = new ExpenseInfo();
                expenseInfo.setExpenseId(e.getExpenseId());
                expenseInfo.setExpenseTitle(e.getExpenseTitle());
                expenseInfo.setAmount(e.getAmount());
                expenseInfo.setNote(e.getNote());
                expenseInfo.setInsert_dt(e.getInsertDate());
                expenseInfo.setCategory(e.getCategory());
                expenseInfoList.add(expenseInfo);
            }
            getAllExpenseResponse.setExpenseInfos(expenseInfoList);
            logger.debug(logPrefix + expenseInfoList.size() + " Expenses retrieved for customerId [" + user.get().getCustomerId() + "]");
        }
        logger.debug(logPrefix + "[" + LOG_EXIT + "]");
        return getAllExpenseResponse;
    }

    @Override
    public GetAllExpenseResponse getAllExpensesByCategory(long customerId, Category category) {
        String logPrefix = "getAllExpensesByCategory()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        GetAllExpenseResponse getAllExpenseResponse = new GetAllExpenseResponse();
        Optional<UserEntity> user = userManagementDao.findById(customerId);
        if (user.isPresent()) {
            logger.debug(logPrefix + "User found with customerId [" + user.get().getCustomerId() + "]");
            List<ExpenseEntity> expenseEntity = expenseDao.findExpenseByUserAndCategory(user.get(), category);
            List<ExpenseInfo> expenseInfoList = new ArrayList<>();
            for (ExpenseEntity e : expenseEntity) {
                ExpenseInfo expenseInfo = new ExpenseInfo();
                expenseInfo.setExpenseId(e.getExpenseId());
                expenseInfo.setExpenseTitle(e.getExpenseTitle());
                expenseInfo.setAmount(e.getAmount());
                expenseInfo.setNote(e.getNote());
                expenseInfo.setInsert_dt(e.getInsertDate());
                expenseInfo.setCategory(e.getCategory());
                expenseInfoList.add(expenseInfo);
            }
            getAllExpenseResponse.setExpenseInfos(expenseInfoList);
            logger.debug(logPrefix + expenseInfoList.size() + " Expenses retrieved for customerId [" + user.get().getCustomerId() + "]");
        }
        logger.debug(logPrefix + "[" + LOG_EXIT + "]");
        return getAllExpenseResponse;
    }

    @Override
    public GetAllExpenseResponse getAllExpensesByDate(long customerId, String date) {
        String logPrefix = "getAllExpensesByDate()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        try {
            GetAllExpenseResponse getAllExpenseResponse = new GetAllExpenseResponse();
            Optional<UserEntity> user = userManagementDao.findById(customerId);
            if (user.isPresent()) {
                logger.debug(logPrefix + "User found with customerId [" + user.get().getCustomerId() + "]");
                String format = "yyyy-MM-dd";
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                List<ExpenseEntity> expenseEntity = expenseDao.findExpenseByUserAndDate(user.get(), new Timestamp(dateFormat.parse(date).getTime()));
                List<ExpenseInfo> expenseInfoList = new ArrayList<>();
                for (ExpenseEntity e : expenseEntity) {
                    ExpenseInfo expenseInfo = new ExpenseInfo();
                    expenseInfo.setExpenseId(e.getExpenseId());
                    expenseInfo.setExpenseTitle(e.getExpenseTitle());
                    expenseInfo.setAmount(e.getAmount());
                    expenseInfo.setNote(e.getNote());
                    expenseInfo.setInsert_dt(e.getInsertDate());
                    expenseInfo.setCategory(e.getCategory());
                    expenseInfoList.add(expenseInfo);
                }
                getAllExpenseResponse.setExpenseInfos(expenseInfoList);
                logger.debug(logPrefix + expenseInfoList.size() + " Expenses retrieved for customerId [" + user.get().getCustomerId() + "]");
            }
            logger.debug(logPrefix + "[" + LOG_EXIT + "]");
            return getAllExpenseResponse;
        } catch (ParseException pe) {
            logger.error(logPrefix + pe.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public ExpenseResponse deleteExpenseById(long expenseId) {
        String logPrefix = "deleteExpenseById()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        long deletedExpenseId = expenseDao.deleteExpenseByExpenseId(expenseId);
        if (deletedExpenseId > 0) {
            return new ExpenseResponse(true, "Record deleted successfully!");
        } else {
            return new ExpenseResponse(false, "Record deletion failed!");
        }
    }

    @Override
    @Transactional
    public ExpenseResponse updateExpenseById(long expenseId, long amount) {
        String logPrefix = "updateExpenseById()";
        logger.debug(logPrefix + "[" + LOG_ENTRY + "]");
        long updatedExpenseId = expenseDao.updateExpenseAmountByExpenseId(amount, expenseId);
        if (updatedExpenseId > 0) {
            return new ExpenseResponse(true, "Record updated successfully!");
        } else {
            return new ExpenseResponse(false, "Record update failed!");
        }
    }
}
