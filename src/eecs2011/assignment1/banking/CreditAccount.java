package eecs2011.assignment1.banking;

public class CreditAccount extends Account {
    
    private static double HIGH_CREDIT_LIMIT = 1000;
    private static double CREDIT_EXCEED_LIMIT_PENALTY = 29;
    
    private double creditExceedLimitCharge;
    
    public CreditAccount(String accountSIN) {
        super(accountSIN);
        this.creditExceedLimitCharge = 0;
    }

    @Override
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

    public double getCreditExceedLimitCharge() {
        return creditExceedLimitCharge;
    }

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
