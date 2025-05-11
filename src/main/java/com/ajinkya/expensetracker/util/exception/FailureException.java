package com.ajinkya.expensetracker.util.exception;

import com.ajinkya.expensetracker.util.enums.FailureEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FailureException extends RuntimeException {
    private FailureEnum failureEnum;
    public FailureException(String message, FailureEnum failureEnum) {
        super(message);
        this.failureEnum = failureEnum;
    }
}
