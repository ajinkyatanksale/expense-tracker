package com.ajinkya.expensetracker.dto.expense;

import com.ajinkya.expensetracker.util.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewExpenseRequest {
    @NotBlank
    private String expenseTitle;
    @Positive
    private long amount;
    @NotNull
    private String note;
    @NotNull
    private Category category;
}
