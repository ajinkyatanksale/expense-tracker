package com.ajinkya.expensetracker.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="expense", schema="expense_tracker")
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
    @Column(name="insert_dt")
    private Timestamp insertDate;

    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }

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

    public Timestamp getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }
}
