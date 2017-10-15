package eecs2011.assignment1.banking;

/**
 * The abstract class of all types of accounts.
 * @author daniel
 *
 */
public abstract class Account {

    protected String accountSIN;
    protected double balance;
    protected double limit;
    protected boolean suspended;
    protected boolean cancelled;
    
    
    public Account(String accountSIN) {
        this.accountSIN = accountSIN;
        this.balance = 0;
        this.suspended = false;
        this.cancelled = false;
    }
    
    
    public abstract void withdrawAmount(double amount);
    
    public abstract double getOutstandingCharge();
    
    public abstract void clearOutstandingCharge();

    public void depositAmount(double amount) {
    	if (this.suspended) {
    		throw new IllegalStateException("Account is suspended");
    	}
    	if (amount <= 0) {
    		throw new IllegalArgumentException("Deposit value is of value less than 0");
    	}
    	this.balance += amount;
    }

    public void cancel() {
    	this.cancelled = true;
    }

    public void suspend() {
    	this.suspended = true;
    }
    
    public void reactivate() {
        this.suspended = false;
    }
    
    public void setLimit(double limit) {
        this.limit = limit;
    }
    
    public double getLimit() {
        return limit;
    }

    public double getBalance() {
    	return this.balance;
    }

    public String getAccountSIN() {
        return accountSIN;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
