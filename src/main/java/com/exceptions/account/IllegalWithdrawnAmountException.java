/**
 *
 * @author Idris TSAFACK
 */

package com.exceptions.account;


public class IllegalWithdrawnAmountException extends Exception {
    
    private Double illegalWithdrawnAmount;
    
    public IllegalWithdrawnAmountException(Double negativeWithdrawnAmount) {
        this.illegalWithdrawnAmount = negativeWithdrawnAmount;
    }

    public Double getIllegalWithdrawnAmount() {
        return this.illegalWithdrawnAmount;
    }
     
    @Override
    public String toString() {
        return "Illegal amount to Withdraw: " + this.illegalWithdrawnAmount;
    }
    
}
