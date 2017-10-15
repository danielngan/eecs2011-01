package eecs2011.assignment1.banking;

/**
 * @author Brian
 *
 */
public class CheckingAccount {
	private String accountSIN;
	private double balance;
	private OverdraftProtection overdraftProtection;
	private boolean suspend;
	private double debt; // short for indebtedness
	private double limit; //overdraft limit
	private double cLimit; //current over draft value.
	private boolean canceled;
	
	/**
	 * @param SIN1 is Social Security number given by the customer
	 * @param ODP1 is the Overdraft Protection Option. Available Options are "None", "MFF" (monthly fixed fee), "PPUF" (Pay Per Use Fee)
	 */
	private CheckingAccount(String accountSIN, OverdraftProtection overdraftProtection) {
		this.accountSIN = accountSIN;
		this.balance = 0;
		this.overdraftProtection = overdraftProtection;
		this.suspend = false;
		this.debt = 0;
		this.limit = 0;
		this.cLimit = 0;
	}
	
	private static boolean containsOnlyNum(String value) {
		for(int i = 0; i < value.length(); i++) {
			if(!Character.isDigit(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param n is the amount of money being withdrawn
	 * 
	 */
	private void withdrawAmount(double n) {
		if (n > this.balance) {
			switch (this.overdraftProtection) {
			case NONE: 
				// 25 Dollar amount charge for nsf fee, not specified inside assignment documents.
				this.debt -= 25;
				break;
			case PAY_PER_USE:
				// n is added to debt amount and customer can withdraw n dollars. Fee of 5 dollars is applied.
				if (this.cLimit <= this.limit) {//if the current overdraft value is more than the overdraft limit, then a NSF fee will be applied on top of the current indebtness
					this.debt -= n;
					this.cLimit -= n;
					this.debt -= 25;
				} else {
					this.debt -= 5;
					this.debt -= n;
				}
				break;
			case MONTHLY_FIXED_FEE:
				// n is added to debt amount and customer can withdraw n dollars.
				if (this.cLimit <= this.limit) {//if the current overdraft value is more than the overdraft limit, then a NSF fee will be applied on top of the current indebtness
					this.debt -= n;
					this.cLimit -= n;
					this.debt -= 25;
				} else {
					this.debt -= n;
					this.cLimit -= n;
				}
				break;
			}
		}
		else {
			this.balance -= n;			
		}
		//record log activity
	}	
	
	/**
	 * @param n
	 * @return
	 */
	public String depositAmount(double n) {
		if (this.suspend) {
			throw new IllegalStateException("Account is suspended");
		}
		if (n <= 0) {
			throw new IllegalArgumentException("Deposit value is of value less than 0");
		}
		this.balance += n;
		return "Successfully Deposited " + "$" + n + " to your checking account";
		//record log activity
	}
	
	public static CheckingAccount createAccount(String accountSIN, OverdraftProtection overdraftprotection) { //call by Checking_account name = Checking_account.createAccount(x,x);
		if(accountSIN == null || accountSIN.length () == 0 || !CheckingAccount.containsOnlyNum(accountSIN)) {
			throw new IllegalArgumentException("Invalid SIN: " + accountSIN);
		}
		return new CheckingAccount(accountSIN, overdraftprotection);
	}
	
	public void cancelAccount() {
		canceled = true;
	}
	
	/**
	 * Will toggle the account's suspension state. If an account is suspended any withrawal, deposit, or transfer options are unavailable.
	 */
	private void suspendAccount() {
		this.suspend = !this.suspend;
	}
	
	/**
	 * @return returns the balance of the provided checking account.
	 */
	private double getBalance() {
		return this.balance;
	}
	
	private void setLimit(double x) {
		this.limit = -x;
	}
	
	private void transferAmount(double x, Credit_account y) {
		if(this.suspend) {
			throw new IllegalStateException("Account is suspended");
		}
		this.balance -= x;
		y.depositAmount(x);
	}
	
	private void setOverdraftOption(String str) {
		if(!(str.equals("None") || str.equals("MFF") || str.equals("PPUF"))) {
			throw new IllegalArgumentException("Invalid Overdraft Selection Option, please select between None, MFF (Monthly Fixed Fee), PPUF (Pay Per Use Fee)");
		}
		this.ODP = str;
		if(this.ODP.equals("MFF")) {// if MFF is chosen, a 4 dollar charge will be applied at the beginning of every month.
			this.debt = -4;
		}
	}
	
	private void terminateAccount(Checking_account a) {
		Demand_loan_account.createAccount(this.SIN, this.debt);
		a = null;
	}
	
}
