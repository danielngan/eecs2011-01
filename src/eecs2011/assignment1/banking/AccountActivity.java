package eecs2011.assignment1.banking;
import java.util.Date;

public class AccountActivity {
	

	private AccountActivityType activityType;
	private Date activityDateTime;
	private String accountSIN;
	private double currentBalance;
	private double changeOfBalance;
	private OverdraftProtection overdraftOption;
	private double overdraftLimit;
	private String transferAccountSIN;
	private double transferAmount;
	private TransferDirection transferDirection;
	
	public AccountActivity() {
		super();
	}
	public AccountActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(AccountActivityType activityType) {
		this.activityType = activityType;
	}
	public Date getActivityDateTime() {
		return activityDateTime;
	}
	public void setActivityDateTime(Date activityDateTime) {
		this.activityDateTime = activityDateTime;
	}
	public String getAccountSIN() {
		return accountSIN;
	}
	public void setAccountSIN(String accountSIN) {
		this.accountSIN = accountSIN;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public double getChangeOfBalance() {
		return changeOfBalance;
	}
	public void setChangeOfBalance(double changeOfBalance) {
		this.changeOfBalance = changeOfBalance;
	}
	public OverdraftProtection getOverdraftOption() {
		return overdraftOption;
	}
	public void setOverdraftOption(OverdraftProtection overdraftOption) {
		this.overdraftOption = overdraftOption;
	}
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}
	public String getTransferAccountSIN() {
		return transferAccountSIN;
	}
	public void setTransferAccountSIN(String transferAccountSIN) {
		this.transferAccountSIN = transferAccountSIN;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	public TransferDirection getTransferDirection() {
		return transferDirection;
	}
	public void setTransferDirection(TransferDirection transferDirection) {
		this.transferDirection = transferDirection;
	}
	
	
	
	
}
