package com.ajinkya.expensetracker.dao;

import com.ajinkya.expensetracker.entity.ExpenseEntity;
import com.ajinkya.expensetracker.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseDao extends CrudRepository<ExpenseEntity, Long>  {
    List<ExpenseEntity> findExpenseByUser(UserEntity user);
}
