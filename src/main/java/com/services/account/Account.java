/**
 *
 * @author Idris TSAFACK
 */

package com.services.account;

import com.exceptions.account.IllegalAddedAmountException;
import com.exceptions.account.IllegalBalanceException;
import com.exceptions.account.IllegalWithdrawnAmountException;
import java.util.List;

/**
 * This class represents a simple account.
 * It doesn't handle different currencies, all money is supposed to be of standard currency EUR.
 */

public interface Account {
    
    /**
     * Adds money to this account.
     * @param addedAmount - the money to add
     * @throws IllegalAddedAmountException
     */
    public void add(Double addedAmount) throws IllegalAddedAmountException;
    
    /**
     * Withdraws money from the account.
     * @param withdrawnAmount - the money to withdraw
     * @param rule - the AccountRule that defines which balance is allowed for this account
     * @return the remaining account balance
     * @throws IllegalBalanceException if the withdrawal leaves the account with a forbidden balance
     * @throws IllegalWithdrawnAmountException if the amount to withdraw is negative
     */
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException, IllegalWithdrawnAmountException;
    
    /**
     * Gets the current account balance.
     * @return the account's balance
     */
    public Double getBalance();
    
    /**
     * Get the list of operations relating to the account
     * @return a String representing the list of operations relating to the account
     */
    public List<AccountStatement> getAccountStatementList();
 
    /**
     * Prints the account statement
     * @return a String representing the list of operations relating to the account
     */
    public String printAccountStatement();

}
