/**
 *
 * @author Idris TSAFACK
 * 
 */

package com.test.account;

import com.services.account.AccountImpl;
import com.services.account.AccountRuleImpl;
import com.exceptions.account.IllegalAddedAmountException;
import com.exceptions.account.IllegalWithdrawnAmountException;
import com.exceptions.account.IllegalBalanceException;
import com.services.account.AccountRule;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.*;
import org.junit.rules.ExpectedException;
import com.services.account.Account;


/**
 * 
 * I've Implemented the test first, then develop the code that makes it pass.
 * I've then focus on the second test, and so on.
 * 
 */

@RunWith(JUnitParamsRunner.class)
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @BeforeClass
    public static void beforeTests() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("=> Begin of Test Methods of Customer Account Management");
        System.out.println("---------------------------------------------------------------");
    }
    
    
    /**
     * Will be executed before each Test Method
     */
    @Before
    public void setUp() {
        this.customerAccount = new AccountImpl();   //Automatically instanciate an empty account
        this.rule = new AccountRuleImpl();
    }
    
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        System.out.println("\n => Testing method : testAccountWithoutMoneyHasZeroBalance ");
        Assert.assertEquals(0.0, this.customerAccount.getBalance(), 0);
    }
    

    /**
     * Adds a positive money to the account and checks that the new balance is as expected.
     * @throws com.exceptions.IllegalAddedAmountException when trying to add a negative amount
     */
    @Test
    public void testAddPositiveAmount() throws IllegalAddedAmountException {
        System.out.println("\n => Testing method : testAddPositiveAmount ");
        Double oldBalance = this.customerAccount.getBalance();
        Double addedAmount = 18.25;
        this.customerAccount.add(addedAmount);  //Adding the money
        Assert.assertEquals( oldBalance + addedAmount, this.customerAccount.getBalance(), 0);
    }
 
    /**
     * Adds a positive money to the account and checks that the new balance is as expected.
     * @throws com.exceptions.IllegalAddedAmountException when trying to add a negative amount
     */
    @Test
    public void testPrintAccountStatement() throws IllegalAddedAmountException, IllegalBalanceException, IllegalWithdrawnAmountException {
        System.out.println("\n => Testing method : testPrintAccountStatement ");
        Double oldBalance = this.customerAccount.getBalance();
        Double addedAmount = 18.25;
        // Deposit
        this.customerAccount.add(addedAmount); 
        // Withdrawal
        this.customerAccount.withdrawAndReportBalance(10.0, this.rule); 
        // Printing the account statement
        this.customerAccount.printAccountStatement();
        // Asserting that there are '2' operations registered for this account
        Assert.assertEquals( 2, this.customerAccount.getAccountStatementList().size(), 0);
    }
    
    /**
     * This is a "Parameterized Test" with JUnitParams 1.0.2, the "data Set" for Test is provided by a utility function 
     * 
     * Tests that an "illegal withdrawal" throws the expected exception.
     * I've used the logic contained in CustomerAccountRule.
     * @throws IllegalBalanceException, IllegalWithdrawnAmountException
     */
    @Test
    @Parameters (method = "dataSetProviderForIllegalWithdrawalTest")
    public void testWithdrawAndReportBalanceIllegalBalance(Account customerAccountProvided, Double withdrawnAmountProvided, AccountRule ruleProvided, Double resultingAccountBalanceProvided) 
            throws IllegalBalanceException, IllegalWithdrawnAmountException {
        
        System.out.println("\n => Testing method : testWithdrawAndReportBalanceIllegalBalance ");
        
        //Expected features of the Exception Class to throw
        thrown.expect(IllegalBalanceException.class);   
        thrown.expect(hasProperty("balance", is(resultingAccountBalanceProvided)));     
        
        //will attempt 4 illegals withdrawal on "customerAccountProvided", according to the entries of the data set provider (function "dataSetProviderForIllegalWithdrawalTest")
        customerAccountProvided.withdrawAndReportBalance(withdrawnAmountProvided, ruleProvided);  
         
    }

    /**
     * This is a "the test data set" provider for the Parameterized Test above" with JUnitParams 1.0.2
     * It will provide a set of 4 quadruplets for test data set, on which will be iterated the withdrawal test method above
     *@return an object containing the "testWithdrawAndReportBalanceIllegalBalance" test data set
     */
    private Object[] dataSetProviderForIllegalWithdrawalTest() {       
        Account aCustomerAccount = new AccountImpl();  //The account to deal with (automatically instanciate an empty account)   
        AccountRule aRule = new AccountRuleImpl();  //The account rule

        //credit the account of 1250
        try { 
            Double addedAmount = 1250.0;
            aCustomerAccount.add(addedAmount);
        } 
        catch (IllegalAddedAmountException e) {
            System.out.println(e.toString());
        }

        //Set of withdrawal amounts and resulting balances not to be authorized      
        Double withdrawnAmount1 = 1251.0; 
        Double resultingAccountBalance1 = -1.0; 
        
        Double withdrawnAmount2 = 2600.0; 
        Double resultingAccountBalance2 = -1350.0;
        
        Double withdrawnAmount3 = 12356.0; 
        Double resultingAccountBalance3 = -11106.0; 
        
        Double withdrawnAmount4 = 4572.0;  
        Double resultingAccountBalance4 = -3322.0;   
        
        //We return the Set data for withdrawal test attempts to be made on the account
        //This Object contains the test data set that will be used by the test method named "testWithdrawAndReportBalanceIllegalBalance"
        return new Object[]{
            new Object[]{aCustomerAccount, withdrawnAmount1, aRule, resultingAccountBalance1},
            new Object[]{aCustomerAccount, withdrawnAmount2, aRule, resultingAccountBalance2},
            new Object[]{aCustomerAccount, withdrawnAmount3, aRule, resultingAccountBalance3},
            new Object[]{aCustomerAccount, withdrawnAmount4, aRule, resultingAccountBalance4}
        };
    }    
    

    /**
     * Test that the AccountImpl Rule authorizes a withdrawal if the resulting account balance after a withdrawal is >= 0
     */
    @Test
    public void testWithdrawPermitionIsTrue() {
        System.out.println("\n => Testing method : testWithdrawPermitionIsTrue ");
        
        Double resultingAccountBalance = 240.6;
        Assert.assertTrue(this.rule.withdrawPermitted(resultingAccountBalance)); 
    }    

    
    /**
     * Test that the AccountImpl Rule does not authorizes a withdrawal 
 if the resulting account balance after a withdrawal is < 0
     */
    @Test
    public void testWithdrawPermitionIsFalse() {
        System.out.println("\n => Testing method : testWithdrawPermitionIsFalse ");
        
        Double resultingAccountBalance = -11.6;
        Assert.assertFalse(this.rule.withdrawPermitted(resultingAccountBalance)); 
    }      
    
    
    /**
     * Tests that a "Legal withdrawal" produces a good balance
     * @throws IllegalAddedAmountException, IllegalBalanceException, IllegalWithdrawnAmountException
     */
    @Test
    public void testWithdrawAndReportBalanceLegalBalance() throws IllegalAddedAmountException, IllegalBalanceException, IllegalWithdrawnAmountException {
        System.out.println("\n => Testing method : testWithdrawAndReportBalanceLegalBalance ");
        
        Double addedAmount = 2300.0;       
        Double withdrawnAmount = 700.0;             
        
        //Adding the amount of 2300.0 
        this.customerAccount.add(addedAmount); 
        Double oldBalance = this.customerAccount.getBalance();         
        //Issuing a withdraw of 700.0 out of 2300.0 with the AccountImpl Rule
        Double newBalance = this.customerAccount.withdrawAndReportBalance(withdrawnAmount, this.rule); 
        
        Assert.assertEquals( oldBalance - withdrawnAmount, newBalance, 0);
    }    

    
    /**
     * Adds a negative money to the account and checks that it throws the IllegalAddedAmountException
     * @throws IllegalAddedAmountException when trying to add a negative amount
     */
    @Test
    public void testAddNegativeAmount() throws IllegalAddedAmountException {
        System.out.println("\n => Testing method : testAddNegativeAmount ");

        Double negativeAddedAmount = -425.6;
        
        //Expected features of the Exception Class to throw
        thrown.expect(IllegalAddedAmountException.class);   
        thrown.expect(hasProperty("illegalAddedAmount", is(negativeAddedAmount)));
        
        //Trying to add a negative amount will generate the IllegalAddedAmountException
        this.customerAccount.add(negativeAddedAmount);  
    }    
 
    
    /**
     * Withdraw a negative money to the account and checks that it throws the IllegalWithdrawnAmountException
     * @throws IllegalWithdrawnAmountException, IllegalAddedAmountException, IllegalBalanceException
     */
    @Test
    public void testWithdrawNegativeAmount() throws IllegalWithdrawnAmountException, IllegalAddedAmountException, IllegalBalanceException {        
        System.out.println("\n => Testing method : testWithdrawNegativeAmount ");
        
        Double negativeWithdrawAmount = -187.48;
        
        //Expected features of the Exception Class to throw
        thrown.expect(IllegalWithdrawnAmountException.class);   
        thrown.expect(hasProperty("illegalWithdrawnAmount", is(negativeWithdrawAmount)));
        
        //Trying to add a negative amount will generate the IllegalAddedAmountException
        this.customerAccount.withdrawAndReportBalance(negativeWithdrawAmount, this.rule);  
    }  
    
    
    @AfterClass
    public static void afterTests() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("=>End of Test Methods of Customer Account Management");
        System.out.println("---------------------------------------------------------------");
    }
    
}
