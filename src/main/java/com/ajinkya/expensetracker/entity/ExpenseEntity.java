package com.ajinkya.expensetracker.entity;

import com.ajinkya.expensetracker.util.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name="expense", schema="expense_tracker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseEntity {
    @Id
    @Column(name="expense_id")
    @SequenceGenerator(name="expense_id_seq", sequenceName="expense_tracker.expense_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "expense_id_seq")
    private long expenseId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity user;
    @Column(name="expense_title")
    private String expenseTitle;
    @Column(name="amount")
    private long amount;
    @Column(name="note")
    private String note;
    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;
    @Column(name="insert_dt")
    private Timestamp insertDate;

}
