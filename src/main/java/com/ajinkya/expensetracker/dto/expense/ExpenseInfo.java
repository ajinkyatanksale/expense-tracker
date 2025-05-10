package com.ajinkya.expensetracker.dto.expense;


import com.ajinkya.expensetracker.util.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseInfo {
    private long expenseId;
    private String expenseTitle;
    private long amount;
    private String note;
    private Timestamp insert_dt;
    private Category category;
}
