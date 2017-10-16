package eecs2011.assignment1.banking;

/**
 * The abstract class of all types of accounts.
 * @author daniel and brian
 *
 */
public abstract class Account {

    protected String accountSIN;
    protected double balance;
    protected double limit;
    protected boolean suspended;
    protected boolean cancelled;
    
    /**
     * custom constructor of the account
     * @param accountSIN
     */
    public Account(String accountSIN) {
        this.accountSIN = accountSIN;
        this.balance = 0;
        
    }
    
    
    
    /**
     * @precondition the amount is not negative
     * withdraw a certain amount from the account
     * @param amount
     * @postcondition An amount of money is withdrawn from the account.
     */
    public abstract void withdrawAmount(double amount);
    
    public abstract double getOutstandingCharge();
    
    public abstract void clearOutstandingCharge();
    
    /**
     * deposit a certain amount of money into the account.
     * @precondition the account is not suspended
     * @precondition the amount is not negative
     * @postcondition the current balance is now equal to the original balance plus the deposit amount
     * @param amount
     */
    public void depositAmount(double amount) {
    	if (this.suspended) {
    		throw new IllegalStateException("Account is suspended");
    	}
    	if (amount <= 0) {
    		throw new IllegalArgumentException("Deposit value is of value less than 0");
    	}
    	this.balance += amount;
    }
    
    /**
     * @precondition the account exists
     * to cancel a bank account
     * @postcondition the account is cancelled.
     */
    public void cancelAccount() {
    	this.cancelled = true;
    }
    
    /**
     * @precondition the account exists
     * @precondition the account is not suspended
     * Suspend a bank account
     * @postcondtion the account can't undergo any operations since then
     */
    public void suspendAccount() {
    	this.suspended = true;
    }
    
    /**
     * @precondition the account exists
     * @precondition the account is suspended
     * reactive a suspended account
     * @postcondition the account can undergo operations since then
     */
    public void reactiveAccount() {
        this.suspended = false;
    }
    
    /**
     * @precondition the limit is not negative
     * Set the Overdraft Limit Amount of a specified checking account to a specified
		amount; or set the Credit Limit of a specified credit account to a specified amount
     * @param limit
     * @postcondition the overdraft limit/credit limit of a checking account/credit account is set.
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }
    
    /**
     * @precondition the limit is not null
     * return the overdraft limit/credit limit of a checking account/credit account.
     * @return the limit.
     * @postcondition the overdraft limit/credit limit of a checking account/credit account is returned.
     */
    public double getLimit() {
        return limit;
    }
    
    /**
     * @precondition the current balance of the account is not negative.
     * Return the current balance of an account
     * @return the current balance of a account.
     * @postcondition the current balance of the account is returned.
     */
    public double getBalance() {
    	return this.balance;
    }
    
    /**
     * @precondition the customer provides a SIN number
     * @return the Social Insurance Number of a account.
     * @postcondition the social insurance number is returned.
     */
    public String getAccountSIN() {
        return accountSIN;
    }
    
    /**
     * @precondition the account exists
     * Check whether an account is suspended or not.
     * @return a boolean variable that indicates whether or not the account is suspended.
     * @postcondition a boolean variable that shows whether the account is suspended is returned.
     */
    public boolean isSuspended() {
        return suspended;
    }
    
    /**
     * @precondition the account exists
     * Check whether an account is cancelled or not.
     * @return a boolean variable that indicates whether the account is cancelled or not.
     * @postcondition a boolean variable that shows whether the account is cancelled is returned.
     */
    public boolean isCancelled() {
        return cancelled;
    }
}
