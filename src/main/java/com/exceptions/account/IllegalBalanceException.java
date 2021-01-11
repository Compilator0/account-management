/**
 *
 * @author Idris TSAFACK
 */

package com.exceptions.account;


public class IllegalBalanceException extends Exception {
        
    private Double balance;
    
    public IllegalBalanceException(Double illegalBalance) {
        this.balance = illegalBalance;
    }

    public Double getBalance() {
        return this.balance;
    }       
    
    @Override
    public String toString() {
        return "Illegal account balance: " + this.balance;
    }
}
