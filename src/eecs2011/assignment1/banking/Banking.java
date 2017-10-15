package eecs2011.assignment1.banking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Banking {

    private Map<String, CheckingAccount> checkingAccounts = new HashMap<String, CheckingAccount>();
    private Map<String, CreditAccount> creditAccounts = new HashMap<String, CreditAccount>();
    private AccountActivityLog accountActivityLog = new AccountActivityLog();
    
    private CheckingAccount getCheckingAccount(String accountSIN) {
        CheckingAccount account = checkingAccounts.get(accountSIN);
        if (account == null) {
            throw new IllegalArgumentException("No such Checking Account for SIN = " + accountSIN);
        }
        return account;
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

    public void withdrawAmount(AccountType accountType, String accountSIN, double amount) {
        Account account = getAccount(accountType, accountSIN);
        account.withdrawAmount(amount);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.WITHDRAW_AMOUNT, accountType);
        record.setTransactionAmount(amount);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }

    public void setOverdraftOption(String accountSIN, OverdraftProtection overdraftOption) {
        CheckingAccount account = getCheckingAccount(accountSIN);
        account.setOverdraftOption(overdraftOption);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.SET_OVERDRAFT_OPTION, AccountType.CHECKING);
        record.setOverdraftOption(overdraftOption);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }

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

    public CheckingAccount createCheckingAccount(String accountSIN, OverdraftProtection overdraftProtection) { //call by Checking_account name = Checking_account.createAccount(x,x);
        ensureValidAccountSIN(accountSIN);
        if (checkingAccounts.containsKey(accountSIN)) {
            throw new IllegalStateException("Checking account already exists for SIN: " + accountSIN);
        }
        CheckingAccount account = new CheckingAccount(accountSIN, overdraftProtection);
        checkingAccounts.put(accountSIN, account);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.CREATE_ACCOUNT, AccountType.CHECKING);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
        return account;
    }

    public CreditAccount createCreditAccount(String accountSIN) { //call by Checking_account name = Checking_account.createAccount(x,x);
        ensureValidAccountSIN(accountSIN);
        if (creditAccounts.containsKey(accountSIN)) {
            throw new IllegalStateException("Credit account already exists for SIN: " + accountSIN);
        }
        CreditAccount account = new CreditAccount(accountSIN);
        creditAccounts.put(accountSIN, account);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.CREATE_ACCOUNT, AccountType.CREDIT);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
        return account;
    }

    public void cancelAccount(AccountType accountType, String accountSIN) {
        Account account = getAccount(accountType, accountSIN);
        account.cancel();
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.CANCEL_ACCOUNT, accountType);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }

    public void suspendAccount(AccountType accountType, String accountSIN) {
        Account account = getAccount(accountType, accountSIN);
        account.suspend();
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.SUSPEND_ACCOUNT, accountType);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
    }

    public double getBalance(AccountType accountType, String accountSIN) {
        Account account = getAccount(accountType, accountSIN);
        ensureAccountNotSuspendedOrCancelled(account);
        AccountActivity record = new AccountActivity(accountSIN, new Date(), AccountActivityType.GET_BALANCE, accountType);
        record.setCurrentBalance(account.getBalance());
        record.setCurrentOutstandingCharge(account.getOutstandingCharge());
        accountActivityLog.addRecord(record);
        return account.getBalance();
    }
    
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
        accountActivityLog.sort();
    }
    
    public void processAccountLogEndOfDay() {
        accountActivityLog.processEndOfDay();
    }
    
    public void processAccountLogEndOfMonth() {
        accountActivityLog.processEndOfMonth();
    }
    
    public void saveAccountLog(String fileName) {
        accountActivityLog.saveToFile(fileName);
    }
    
    public void retrieveAccountLog(String fileName) {
        accountActivityLog.retrieveFromFile(fileName);
    }
 
}
