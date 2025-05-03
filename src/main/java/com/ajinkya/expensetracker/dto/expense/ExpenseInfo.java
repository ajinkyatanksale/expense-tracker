package com.ajinkya.expensetracker.dto.expense;


import java.sql.Timestamp;

public class ExpenseInfo {
    private String expenseTitle;
    private long amount;
    private String note;
    private Timestamp insert_dt;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getInsert_dt() {
        return insert_dt;
    }

    public void setInsert_dt(Timestamp insert_dt) {
        this.insert_dt = insert_dt;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }
}
