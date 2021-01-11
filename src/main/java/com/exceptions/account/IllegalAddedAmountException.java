/**
 *
 * @author Idris TSAFACK
 */

package com.exceptions.account;


public class IllegalAddedAmountException extends Exception {
        
    private Double illegalAddedAmount;
    
    public IllegalAddedAmountException(Double pIllegalAddedAmount) {
        this.illegalAddedAmount = pIllegalAddedAmount;
    }

    public Double getIllegalAddedAmount() {
        return this.illegalAddedAmount;
    }
     
    @Override
    public String toString() {
        return "Illegal amount to Add: " + this.illegalAddedAmount;
    }
    
}
