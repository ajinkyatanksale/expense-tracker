package com.ajinkya.expensetracker.dao;

import com.ajinkya.expensetracker.entity.ExpenseEntity;
import com.ajinkya.expensetracker.entity.UserEntity;
import com.ajinkya.expensetracker.util.enums.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ExpenseDao extends CrudRepository<ExpenseEntity, Long>  {
    List<ExpenseEntity> findExpenseByUser(UserEntity user);

    @Query("SELECT e FROM ExpenseEntity e WHERE e.user = :user AND e.expenseTitle = :title")
    List<ExpenseEntity> findExpenseByUserAndTitle(@Param("user") UserEntity user, @Param("title") String title);

    @Query("SELECT e FROM ExpenseEntity e WHERE e.user = :user AND e.category = :category")
    List<ExpenseEntity> findExpenseByUserAndCategory(@Param("user") UserEntity user, @Param("category") Category category);

    @Query("SELECT e FROM ExpenseEntity e WHERE e.user = :user AND DATE(e.insertDate) = :date")
    List<ExpenseEntity> findExpenseByUserAndDate(@Param("user") UserEntity user, @Param("date") Timestamp date);
}
