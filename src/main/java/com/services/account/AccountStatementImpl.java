/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.account;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author idristsafack
 */
public class AccountStatementImpl implements AccountStatement {
    
    private AccountOperationEnum operation;
    private Date date;
    private Double amount;
    private Double balance;

    public AccountStatementImpl(AccountOperationEnum operation, Date date, Double amount, Double balance) {
        this.operation = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "\n {" + "operation=" + operation + ", date=" + date + ", amount=" + amount + ", balance=" + balance + "} \n";
    }

    public AccountOperationEnum getOperation() {
        return operation;
    }

    public void setOperation(AccountOperationEnum operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    
    
    
    
}
