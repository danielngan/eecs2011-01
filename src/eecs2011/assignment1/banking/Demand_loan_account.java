package eecs2011.assignment1.banking;

public class Demand_loan_account {
	private String SIN;
	private double debt;
	
	private Demand_loan_account(String SIN, double debt) {
		this.SIN = SIN;
		this.debt = debt;
	}
	
	public static Demand_loan_account createAccount(String s, double d) {
		return new Demand_loan_account(s, d);
	}
}
