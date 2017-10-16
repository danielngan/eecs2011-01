package eecs2011.assignment1.banking;

import java.io.Serializable;
import java.util.Date;

public class AccountActivity implements Serializable {

    private String accountSIN;
    private Date activityDateTime;
    private AccountActivityType activityType;
    private AccountType accountType;
    private double transactionAmount;
    private double currentBalance;
    private double currentOutstandingCharge;
    private OverdraftProtection overdraftOption;
    private double limit;
    private AccountType transferAccountType;
    private String transferAccountSIN;

    public AccountActivity() {
        super();
    }

    public AccountActivity(String accountSIN, Date activityDateTime, AccountActivityType activityType,
            AccountType accountType) {
        super();
        this.accountSIN = accountSIN;
        this.activityDateTime = activityDateTime;
        this.activityType = activityType;
        this.accountType = accountType;
    }
    /**
     * @precondition accountSIN is not null
     * Get the Social Insurance Number(SIN) of an account.
     * @return the SIN of an account.
     * @postcondition the SIN of an account is returned.
     */
    public String getAccountSIN() {
        return accountSIN;
    }
    
    /**
     * @precondition accountSIN is not null
     * Set the Social Insurance Number (SIN) of an account.
     * @param accountSIN
     * @postcondition the SIN of an account is set.
     */
    public void setAccountSIN(String accountSIN) {
        this.accountSIN = accountSIN;
    }
    
    /**
     * @precondition activityDateTime is not null.
     * Get the Date and Time of an account activity.
     * @return the Date and Time of that account activity.
     * @postcondition The Date and Time of that activity is returned.
     */
    public Date getActivityDateTime() {
        return activityDateTime;
    }
    
    /**
     * @precondition activityDateTime is not null.
     * Set the Date and Time of an account activity.
     * @param activityDateTime
     * @postcondition the activityDateTime of an account activity is set.
     */
    public void setActivityDateTime(Date activityDateTime) {
        this.activityDateTime = activityDateTime;
    }
    
    /**
     * @precondition activityType is not null.
     * Get the type of account activity.
     * @return the type of account activity.
     * @postcondition activityType is returned.
     */
    public AccountActivityType getActivityType() {
        return activityType;
    }

    /**
     * @precondition activityType is not null
     * Set the type of account activity
     * @param activityType
     * @postcondition the activityType is set.
     */
    public void setActivityType(AccountActivityType activityType) {
        this.activityType = activityType;
    }
    
    /**
     * @precondition accountType is not null
     * Get the type of account.
     * @return the type of account.
     * @postcondition The type of account is returned.
     */
    public AccountType getAccountType() {
        return accountType;
    }
    
    /**
     * @precondition accountType is not null
     * Set the type of account.
     * @param accountType
     * @postcondition The type of account is set.
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * @precondition The current balance is not negative
     * Get the current balance.
     * @return the current balance.
     * @postcondition The current balance is returned.
     */
    public double getCurrentBalance() {
        return currentBalance;
    }
    
    /**
     * @precondition current balance is not negative
     * Set the current balance.
     * @param currentBalance
     * @postcondition The current Balance is set.
     */
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    
    /**
     * @precondition Transaction amount is not negative.
     * Get the transaction amount.
     * @return the transactioin amount.
     * @postcondition The transaction amount is returned.
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }
    
    /**
     * @precondition Change of Balance is not null.
     * Set the transaction amount.
     * @param changeOfBalance
     * @postcondition the transaction amount is set.
     */
    public void setTransactionAmount(double changeOfBalance) {
        this.transactionAmount = changeOfBalance;
    }
    
    /**
     * @precondition The current outstanding change is not null.
     * Get the current outstanding change.
     * @return the current outstanding charge.
     * @postcondition The current outstanding charge is returned.
     */
    public double getCurrentOutstandingCharge() {
        return currentOutstandingCharge;
    }
    
    /**
     * @precondition current outstanding charge is not null.
     * Set the current outstanding charge.
     * @param currentOutstandingCharge
     * @postcondition the currentOutstanding charge is set.
     */
    public void setCurrentOutstandingCharge(double currentOutstandingCharge) {
        this.currentOutstandingCharge = currentOutstandingCharge;
    }
    
    /**
     * @precondition OverdraftOption is not null.
     * Get the overdraft option.
     * @return overdraft option.
     * @postcondition the overdraft option is returned.
     */
    public OverdraftProtection getOverdraftOption() {
        return overdraftOption;
    }
    
    /**
     * @precondition overdraftOption is not null
     * Set the overdraft option
     * @param overdraftOption
     * @postcondition the overdraft option is set
     */
    public void setOverdraftOption(OverdraftProtection overdraftOption) {
        this.overdraftOption = overdraftOption;
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
     * @precondition the limit is not null
     * Set the overdraft limit/credit limit of a checking account/credit account.
     * @postcondition the overdraft limit/credit limit of a checking account/credit account is set.
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }
    
    /**
     * @precondition transferAccountType is not null.
     * Get the transfer account type
     * @return the transfer account type
     * @postcondition The transfer account type is returned.
     */
    public AccountType getTransferAccountType() {
        return transferAccountType;
    }
    
    /**
     * @precondition transferAccountType is not null.
     * Set the transfer account type
     * @postcondition The transfer account type is set.
     */
    public void setTransferAccountType(AccountType transferAccountType) {
        this.transferAccountType = transferAccountType;
    }

    /**
     * @precondition transferAccountSIN is not null.
     * Get the transfer account social insurance number(SIN)
     * @return the transfer account SIN number
     * @postcondition The transfer account number (SIN) is returned.
     */
    public String getTransferAccountSIN() {
        return transferAccountSIN;
    }
    
    /**
     * @precondition transferAccountSIN is not null.
     * Set the transfer account social insurance number(SIN)
     * @postcondition The transfer account number (SIN) is returned.
     */
    public void setTransferAccountSIN(String transferAccountSIN) {
        this.transferAccountSIN = transferAccountSIN;
    }

    @Override
    /**
     * @precondition the string is not null.
     * Return the account activity as a string.
     * @return the activity as a string.
     * @postcondition the account activity is returned as a string.
     */
    public String toString() {
        return "AccountActivity [accountSIN=" + accountSIN + ", activityDateTime=" + activityDateTime
                + ", activityType=" + activityType + ", accountType=" + accountType + ", transactionAmount="
                + transactionAmount + ", currentBalance=" + currentBalance + ", currentOutstandingCharge="
                + currentOutstandingCharge + ", overdraftOption=" + overdraftOption + ", limit=" + limit
                + ", transferAccountType=" + transferAccountType + ", transferAccountSIN=" + transferAccountSIN
                + "]";
    }

    
}
