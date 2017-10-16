package eecs2011.assignment1.banking;

public class CreditAccount extends Account {
    
    private static double HIGH_CREDIT_LIMIT = 1000;
    private static double CREDIT_EXCEED_LIMIT_PENALTY = 29;
    
    private double creditExceedLimitCharge;
    
    private CreditAccount(String accountSIN) {
        super(accountSIN);
        this.creditExceedLimitCharge = 0;
        this.suspended = suspended;
        this.cancelled = cancelled;
    }
    
    /**
     * @precondition accountSIN is not null.
     * @param accountSIN
     * create a credit account.
     * @return the new created credit account.
     * @postcondition a new credit account is created.
     */
    public static CreditAccount createAccount(String accountSIN) {
    	CreditAccount newAccount = new CreditAccount(accountSIN);
    	return newAccount;
    }

    @Override
    /**
     * @precondition the amount is not negative
     * withdraw a certain amount from the account
     * @param amount
     * @postcondition An amount of money is withdrawn from the account.
     */
    public void withdrawAmount(double amount) {
        if (balance + limit - amount < 0) {
            if (limit > HIGH_CREDIT_LIMIT) {
                creditExceedLimitCharge += CREDIT_EXCEED_LIMIT_PENALTY;
            }
            // withdraw declined; balance is not deducted
        } else {
            balance -= amount;
        }
    }

    /**
     * @precondition credit exceed limit charge is not null.
     * Get the credit exceed Limit charge.
     * @return the credit exceed Limit charge.
     * @postcondition the credit exceed limit charge is returned.
     */
    public double getCreditExceedLimitCharge() {
        return creditExceedLimitCharge;
    }
    
    /**
     * @precondition credit exceed limit charge is not null.
     * Set the credit exceed Limit charge.
     * @postcondition the credit exceed limit charge is set.
     */
    public void setCreditExceedLimitCharge(double creditExceedLimitCharge) {
        this.creditExceedLimitCharge = creditExceedLimitCharge;
    }

    @Override
    public double getOutstandingCharge() {
        return creditExceedLimitCharge;
    }

    @Override
    public void clearOutstandingCharge() {
        creditExceedLimitCharge = 0;
    }
    
    
}
