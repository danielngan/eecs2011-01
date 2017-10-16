package eecs2011.assignment1.banking;

public class CheckingAccount extends Account {
    
    private static double NON_SUFFICIENT_FUND_PENALTY = 25;
    private static double OVERDRAFT_PAY_PER_USE_FEE = 5;
    private static double OVERDRAFT_MONTHLY_FIXED_FEE = 4;
    
	private OverdraftProtection overdraftOption;
	private double overdraftCharge;

	private CheckingAccount(String accountSIN, OverdraftProtection overdraftProtection) {
	    super(accountSIN);
	    this.suspended = suspended;
	    this.cancelled = cancelled;
	}
	
	/**
	 * @precondition accountSIN and overdraftProtection is not null.
	 * Create a checking account.
	 * @param accountSIN
	 * @param overdraftProtection
	 * @return the new created account.
	 * @postcondition A new checking account is created.
	 */
	public static CheckingAccount createAccount(String accountSIN, OverdraftProtection overdraftProtection) {
		CheckingAccount newAccount = new CheckingAccount(accountSIN, overdraftProtection);
		return newAccount;
	}
	
	
	@Override
	public void withdrawAmount(double amount) {
        if (amount > this.balance) {
            switch (overdraftOption) {
            case NONE:
                // withdraw is declined and the balance is not deducted
                this.overdraftCharge += NON_SUFFICIENT_FUND_PENALTY;// accumulate charge every time an overdraft occurs
                break;
            case PAY_PER_USE:
                this.balance -= amount; // withdraw is allowed
                this.overdraftCharge = OVERDRAFT_PAY_PER_USE_FEE;   // a fixed charge per day; this will be processed and cleared on day-end
                break;
            case MONTHLY_FIXED_FEE:
                this.balance -= amount; // withdraw is allowed
                this.overdraftCharge = OVERDRAFT_MONTHLY_FIXED_FEE; // a fixed charge per month; his will be processed and cleared on month-end
                break;
            }
        } else {
            this.balance -= amount;          
        }
    }

	public void setOverdraftOption(OverdraftProtection overdraftOption) {
        this.overdraftOption = overdraftOption;
    }
	
	public OverdraftProtection getOverdraftOption() {
        return overdraftOption;
    }

    public double getOverdraftCharge() {
	    return overdraftCharge;
	}
	
	public void setOverdraftCharge(double overdraftCharge) {
	    this.overdraftCharge = overdraftCharge;
	}

    @Override
    public double getOutstandingCharge() {
        return overdraftCharge;
    }

    @Override
    public void clearOutstandingCharge() {
        overdraftCharge = 0;
    }
	
}
