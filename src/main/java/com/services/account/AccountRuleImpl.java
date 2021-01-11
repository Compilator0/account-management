/**
 *
 * @author Idris TSAFACK
 * 
 */

package com.services.account;

import java.io.Serializable;


public class AccountRuleImpl implements AccountRule, Serializable {

    private static final long serialVersionUID = -5497135749972551784L;

    public AccountRuleImpl() {
    }
       
    /* (non-Javadoc)
     * @see com.services.account.AccountRule#withdrawPermitted(java.lang.Double)
     */
    @Override
    public boolean withdrawPermitted(Double resultingAccountBalance) {
        return resultingAccountBalance >= 0;
    }

}
