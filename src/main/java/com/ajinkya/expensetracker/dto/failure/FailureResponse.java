package com.ajinkya.expensetracker.dto.failure;

import com.ajinkya.expensetracker.util.enums.FailureEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FailureResponse {
    String failureMessage;
    FailureEnum failureEnum;
}
