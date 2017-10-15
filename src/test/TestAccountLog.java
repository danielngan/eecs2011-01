package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eecs2011.assignment1.banking.AccountActivity;
import eecs2011.assignment1.banking.AccountActivityType;
import eecs2011.assignment1.banking.AccountType;
import eecs2011.assignment1.banking.AccountActivityLog;

public class TestAccountLog {

    @Test
    public void testSortAccountLog() {
        AccountActivityLog accountLog = new AccountActivityLog();
        
        AccountActivity record1 = new AccountActivity("0004", new Date(20000L), AccountActivityType.DEPOSIT_AMOUNT, AccountType.CHECKING);
        AccountActivity record2 = new AccountActivity("0001", new Date(30000L), AccountActivityType.CANCEL_ACCOUNT, AccountType.CREDIT);
        AccountActivity record3 = new AccountActivity("0004", new Date(10000L), AccountActivityType.CREATE_ACCOUNT, AccountType.CHECKING);
        
        accountLog.addRecord(record1);
        accountLog.addRecord(record2);
        accountLog.addRecord(record3);
        
        accountLog.sortAccountLog();
        
        for (int i = 0; i < accountLog.getNumberOfRecords(); i++) {
            System.out.println(accountLog.getRecord(i));
        }
        
    }

}