package com.ajinkya.expensetracker.dto.expense;

public class NewExpenseResponse {
    boolean isRecordCreated;
    String message;

    public boolean isRecordCreated() {
        return isRecordCreated;
    }

    public void setRecordCreated(boolean recordCreated) {
        isRecordCreated = recordCreated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
