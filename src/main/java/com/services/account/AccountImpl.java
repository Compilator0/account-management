/**
 *
 * @author Idris TSAFACK
 */

package com.services.account;

import com.exceptions.account.IllegalBalanceException;
import com.exceptions.account.IllegalWithdrawnAmountException;
import com.exceptions.account.IllegalAddedAmountException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AccountImpl implements Account, Serializable {
    
    private static final long serialVersionUID = -2564191749972551025L;
    
    private Double balance;
    private List<AccountStatement> accountStatementList; 
    
    /**
     * Constructor whithout a param  
     */
    public AccountImpl() {
        //Will automatically instanciate an empty account
        this.balance = 0.0;
        this.accountStatementList = new ArrayList();
    }   
    
    @Override
    public void add(Double addedAmount) throws IllegalAddedAmountException {
        if (addedAmount > 0.0) {      
            this.balance += addedAmount;  //Positive amount is permitted
            // creating a 'DEPOSIT' statement to be registered
            AccountStatement depositStatement = new AccountStatementImpl(AccountOperationEnum.DEPOSIT, new Date(), addedAmount, this.balance);
            // registring this 'DEPOSIT' operation in this account statements list
            this.accountStatementList.add( depositStatement );
        }
        else{
            throw new IllegalAddedAmountException(addedAmount);  //Negative amount can't be added            
        }
    }

    @Override
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException, IllegalWithdrawnAmountException {
        Double resultingAccountBalance = this.balance - withdrawnAmount;
        if( withdrawnAmount > 0){
            if ( rule.withdrawPermitted(resultingAccountBalance) ){
                this.balance -= withdrawnAmount;  //withdrawal is permitted by the account rule
                // creating a 'WITHDRAWAL' statement to be registered
                AccountStatement withdrawalStatement = new AccountStatementImpl(AccountOperationEnum.WITHDRAWAL, new Date(), withdrawnAmount, this.balance);
                // registring this 'WITHDRAWAL' operation in this account statements list
                this.accountStatementList.add( withdrawalStatement );
                return this.balance;
            }
            else throw new IllegalBalanceException(resultingAccountBalance);  //withdrawal is'nt allowed
        }
        else throw new IllegalWithdrawnAmountException(withdrawnAmount);  //can't withdraw a negative amount
    }
   
    @Override
    public String printAccountStatement() {
        System.out.println(this.getAccountStatementList().toString());
        return this.getAccountStatementList().toString();
    }
    
    @Override
    public List<AccountStatement> getAccountStatementList() {
        return this.accountStatementList;
    }

    public void setAccountStatementList(List<AccountStatement> accountStatementList) {
        this.accountStatementList = accountStatementList;
    }
    
    @Override
    public Double getBalance() {
        return this.balance;
    }    
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}
