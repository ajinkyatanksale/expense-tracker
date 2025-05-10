package com.ajinkya.expensetracker.dto.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewExpenseResponse {
    boolean isRecordCreated;
    String message;
}
