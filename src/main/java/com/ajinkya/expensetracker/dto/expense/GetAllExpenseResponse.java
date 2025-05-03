package com.ajinkya.expensetracker.dto.expense;


import java.util.List;

public class GetAllExpenseResponse {
    List<ExpenseInfo> expenseInfos;

    public List<ExpenseInfo> getExpenseInfos() {
        return expenseInfos;
    }

    public void setExpenseInfos(List<ExpenseInfo> expenseInfos) {
        this.expenseInfos = expenseInfos;
    }
}
