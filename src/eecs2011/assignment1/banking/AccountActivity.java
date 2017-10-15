package eecs2011.assignment1.banking;

import java.util.Date;

public class AccountActivity {

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

    public String getAccountSIN() {
        return accountSIN;
    }

    public void setAccountSIN(String accountSIN) {
        this.accountSIN = accountSIN;
    }

    public Date getActivityDateTime() {
        return activityDateTime;
    }

    public void setActivityDateTime(Date activityDateTime) {
        this.activityDateTime = activityDateTime;
    }

    public AccountActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(AccountActivityType activityType) {
        this.activityType = activityType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double changeOfBalance) {
        this.transactionAmount = changeOfBalance;
    }

    public double getCurrentOutstandingCharge() {
        return currentOutstandingCharge;
    }

    public void setCurrentOutstandingCharge(double currentOutstandingCharge) {
        this.currentOutstandingCharge = currentOutstandingCharge;
    }

    public OverdraftProtection getOverdraftOption() {
        return overdraftOption;
    }

    public void setOverdraftOption(OverdraftProtection overdraftOption) {
        this.overdraftOption = overdraftOption;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public AccountType getTransferAccountType() {
        return transferAccountType;
    }

    public void setTransferAccountType(AccountType transferAccountType) {
        this.transferAccountType = transferAccountType;
    }

    public String getTransferAccountSIN() {
        return transferAccountSIN;
    }

    public void setTransferAccountSIN(String transferAccountSIN) {
        this.transferAccountSIN = transferAccountSIN;
    }

    @Override
    public String toString() {
        return "AccountActivity [accountSIN=" + accountSIN + ", activityDateTime=" + activityDateTime
                + ", activityType=" + activityType + ", accountType=" + accountType + ", transactionAmount="
                + transactionAmount + ", currentBalance=" + currentBalance + ", currentOutstandingCharge="
                + currentOutstandingCharge + ", overdraftOption=" + overdraftOption + ", limit=" + limit
                + ", transferAccountType=" + transferAccountType + ", transferAccountSIN=" + transferAccountSIN
                + "]";
    }

    
}
