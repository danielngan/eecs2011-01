package eecs2011.assignment1.banking;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banking {

    private Map<String, CheckingAccount> checkingAccounts = new HashMap<String, CheckingAccount>();
    private Map<String, CreditAccount> creditAccounts = new HashMap<String, CreditAccount>();
    private AccountLog accountActivityLog = new AccountLog();
    
    private CheckingAccount getCheckingAccount(String accountSIN) {
        CheckingAccount account = checkingAccounts.get(accountSIN);
        if (account == null) {
            throw new IllegalArgumentException("No such Checking Account for SIN = " + accountSIN);
        }
        return account;
    }
    /**
     * @precondition CheckingAccounts are not null.
     * Get the checking accounts as a map/
     * @return checking accounts
     * @postcondition the checking accounts are returned.
     */
    public Map<String, CheckingAccount> getCheckingAccounts() {
        return checkingAccounts;
    }
    
    /**
     * @precondition CreditAccounts is not null.
     * Get the credit accounts as a map/
     * @return credit accounts
     * @postcondition the credit accounts are returned.
     */
    public Map<String, CreditAccount> getCreditAccounts() {
        return creditAccounts;
    }
    
    /**
     * @precondition ActivityLog is not null.
     * Get the account activity.
     * @return accountActivityLog
     * @postcondition the account activity log is returned.
     */
    public AccountLog getAccountActivityLog() {
        return accountActivityLog;
    }

    private CreditAccount getCreditAccount(String accountSIN) {
        CreditAccount account = creditAccounts.get(accountSIN);
        if (account == null) {
            throw new IllegalArgumentException("No such Credit Account for SIN = " + accountSIN);
        }
        return account;
    }

    private Account getAccount(AccountType accountType, String accountSIN) {
        if (accountType == AccountType.CHECKING) {
            return getCheckingAccount(accountSIN);
        } else if (accountType == AccountType.CREDIT) {
            return getCreditAccount(accountSIN);
        } else {
            throw new IllegalArgumentException("Invalid account type: " + accountType);
        }
    }
    
    private boolean containsOnlyNum(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private void ensureValidAccountSIN(String accountSIN) {
        if (accountSIN == null || accountSIN.length () == 0 || !containsOnlyNum(accountSIN)) {
            throw new IllegalArgumentException("Invalid SIN: " + accountSIN);
        }
    }
    
    private void ensureAccountNotSuspended(Account account) {
        if (account.isSuspended()) {
            throw new IllegalStateException("Account " + account + " is suspended");
        }
    }
    
    private void ensureAccountNotCancelled(Account account) {
        if (account.isCancelled()) {
            throw new IllegalStateException("Account " + account + " is cancelled");
        }
    }
    
    private void ensureAccountNotSuspendedOrCancelled(Account account) {
        ensureAccountNotSuspended(account);
        ensureAccountNotCancelled(account);
        
    }
    
    /**
     * @precondition the amount is not negative
     * withdraw a certain amount from a account
     * @param amount, accountSIN, accountType
     * @postcondition An amount of money is withdrawn from the account.
     */
    public void withdrawAmount(AccountType accountType, String accountSIN, double amount) {
        Account account = getAccount(accountType, accountSIN);
        account.withdrawAmount(amount);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.WITHDRAW_AMOUNT, accountType);
        record.setTransactionAmount(amount);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }
    /**
     * @precondition overdraftOption is not null
     * Set the overdraft option
     * @param overdraftOption, accountSIN
     * @postcondition the overdraft option is set
     */
    public void setOverdraftOption(String accountSIN, OverdraftProtection overdraftOption) {
        CheckingAccount account = getCheckingAccount(accountSIN);
        account.setOverdraftOption(overdraftOption);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.SET_OVERDRAFT_OPTION, AccountType.CHECKING);
        record.setOverdraftOption(overdraftOption);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }

    /**
     * deposit a certain amount of money into the account.
     * @precondition the account is not suspended
     * @precondition the amount is not negative
     * @postcondition the current balance is now equal to the original balance plus the deposit amount
     * @param amount, accountSIN, accountType
     */
    public void depositAmount(AccountType accountType, String accountSIN, double amount) {
        Account account = getAccount(accountType, accountSIN);
        ensureAccountNotSuspendedOrCancelled(account);
        account.depositAmount(amount);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.DEPOSIT_AMOUNT, accountType);
        record.setTransactionAmount(amount);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }
    
    /**
	 * @precondition accountSIN and overdraftProtection is not null.
	 * Create a checking account.
	 * @param accountSIN, overdraftProtection
	 * @param overdraftProtection
	 * @return the new created account.
	 * @postcondition A new checking account is created.
	 */
    public CheckingAccount createCheckingAccount(String accountSIN, OverdraftProtection overdraftProtection) { //call by Checking_account name = Checking_account.createAccount(x,x);
        ensureValidAccountSIN(accountSIN);
        if (checkingAccounts.containsKey(accountSIN)) {
            throw new IllegalStateException("Checking account already exists for SIN: " + accountSIN);
        }
        CheckingAccount account = CheckingAccount.createAccount(accountSIN, overdraftProtection);
        checkingAccounts.put(accountSIN, account);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.CREATE_ACCOUNT, AccountType.CHECKING);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
        return account;
    }
    
    /**
     * @precondition accountSIN is not null.
     * @param accountSIN
     * create a credit account.
     * @return the new created credit account.
     * @postcondition a new credit account is created.
     */
    public CreditAccount createCreditAccount(String accountSIN) { //call by Checking_account name = Checking_account.createAccount(x,x);
        ensureValidAccountSIN(accountSIN);
        if (creditAccounts.containsKey(accountSIN)) {
            throw new IllegalStateException("Credit account already exists for SIN: " + accountSIN);
        }
        CreditAccount account = CreditAccount.createAccount(accountSIN);
        creditAccounts.put(accountSIN, CreditAccount.createAccount(accountSIN));
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.CREATE_ACCOUNT, AccountType.CREDIT);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
        return account;
    }
    
    
    /**
     * @precondition the account exists
     * to cancel a bank account
     * @param accountType, accountSIN
     * @postcondition the account is cancelled.
     */
    public void cancelAccount(AccountType accountType, String accountSIN) {
        Account account = getAccount(accountType, accountSIN);
        account.cancelAccount();
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.CANCEL_ACCOUNT, accountType);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }
    
    
    /**
     * @precondition the account exists
     * to suspend a bank account
     * @param accountType, accountSIN
     * @postcondition the account is suspended.
     */
    public void suspendAccount(AccountType accountType, String accountSIN) {
        Account account = getAccount(accountType, accountSIN);
        account.suspendAccount();
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.SUSPEND_ACCOUNT, accountType);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }
    
    /**
     * @precondition the current balance of the account is not negative.
     * Return the current balance of an account
     * @param accountType, accountSIN
     * @return the current balance of a account.
     * @postcondition the current balance of the account is returned.
     */
    public double getBalance(AccountType accountType, String accountSIN) {
        Account account = getAccount(accountType, accountSIN);
        ensureAccountNotSuspendedOrCancelled(account);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.GET_BALANCE, accountType);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
        return account.getBalance();
    }
    
    /**
     * @precondition the limit is not negative
     * Set the Overdraft Limit Amount of a specified checking account to a specified
		amount; or set the Credit Limit of a specified credit account to a specified amount
     * @param accountType, accountSIN, limit
     * @postcondition the overdraft limit/credit limit of a checking account/credit account is set.
     */
    public void setLimit(AccountType accountType, String accountSIN, double limit) {
        Account account = getAccount(accountType, accountSIN);
        ensureAccountNotSuspendedOrCancelled(account);
        account.setLimit(limit);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.SET_LIMIT, accountType);
        record.setLimit(limit);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }
    
    /**
     * @precondition the account exists
     * to terminate a bank account
     * @param accountType, accountSIN
     * @postcondition the account is terminated.
     */
    public void terminateAccount(AccountType accountType, String accountSIN) {
        Account account = getAccount(accountType, accountSIN);
        double balance = account.getBalance();
        if (balance != 0) {
            throw new IllegalStateException("Cannot terminate account " + account + " because here is an outstanding balance of " + balance);
        }
        if (accountType == AccountType.CHECKING) {
            checkingAccounts.remove(accountSIN);
        } else if (accountType == AccountType.CREDIT) {
            creditAccounts.remove(accountSIN);
        }
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.TERMINATE_ACCOUNT, accountType);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }
    
    /**
     * @precondition transfer amount is not null.
     * transfer amount 
     * @param fromAccountType, fromAccountSIN, toAccountType, toAccountSIN, amount
     * @postcondition Amount is transferred
     */
    public void transferAmount(AccountType fromAccountType, String fromAccountSIN, AccountType toAccountType, String toAccountSIN, double amount) {
        Account fromAccount = getAccount(fromAccountType, fromAccountSIN);
        Account toAccount = getAccount(toAccountType, toAccountSIN);
        transferAmount(fromAccount, toAccount, amount);
        Date date = new Date();
        // Activity log for the From Account
        AccountActivity record = new AccountActivity(fromAccountSIN, date, AccountActivityType.TRANSFER_AMOUNT_TO, fromAccountType);
        record.setTransactionAmount(amount);
        record.setCurrentBalance(fromAccount.getBalance());
        record.setCurrentOutstandingCharge(fromAccount.getOutstandingCharge());
        record.setTransferAccountType(toAccountType);
        record.setTransferAccountSIN(toAccountSIN);
        accountActivityLog.addRecord(record);
        // Activity log for the To Account
        record = new AccountActivity(toAccountSIN, date, AccountActivityType.TRANSFER_AMOUNT_FROM, toAccountType);
        record.setTransactionAmount(amount);
        record.setCurrentBalance(toAccount.getBalance());
        record.setCurrentOutstandingCharge(toAccount.getOutstandingCharge());
        record.setTransferAccountType(fromAccountType);
        record.setTransferAccountSIN(fromAccountSIN);
        accountActivityLog.addRecord(record);
    }
    

    public void transferAmount(Account fromAccount, Account toAccount, double amount) {
        ensureAccountNotSuspendedOrCancelled(fromAccount);
        ensureAccountNotSuspendedOrCancelled(toAccount);
        ensureAccountNotCancelled(toAccount);
        fromAccount.withdrawAmount(amount);
        toAccount.depositAmount(amount);
    }
    
    public void sortAccountLog() {
        accountActivityLog.sortAccountLog();
    }
    
    public void processAccountLogEndOfDay() {
    }
    
    public void processAccountLogEndOfMonth() {
    }
    
    public void saveAccountLog(String fileName) throws IOException {
        saveAccountLog(new FileOutputStream(fileName));
    }
    
    public void saveAccountLog(OutputStream out) throws IOException {
        XMLEncoder encoder = new XMLEncoder(out, "UTF-8", true, 0);
        try {
            encoder.writeObject(accountActivityLog.getActivityRecords());
            encoder.flush();
        } finally {
            encoder.close();
        }
    }
    
    public void retrieveAccountLog(String fileName) throws IOException {
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
        try {
            List<AccountActivity> records = (List<AccountActivity>)decoder.readObject();
            accountActivityLog = new AccountLog(records);
        } finally {
            decoder.close();
        }
    }
 
}
