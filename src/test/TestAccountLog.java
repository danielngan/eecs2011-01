package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eecs2011.assignment1.banking.AccountActivity;
import eecs2011.assignment1.banking.AccountActivityType;
import eecs2011.assignment1.banking.AccountType;
import eecs2011.assignment1.banking.Banking;
import eecs2011.assignment1.banking.AccountLog;

public class TestAccountLog {

    @Test
    /**
     * Test the sort account log function.
     * @throws IOException
     */
    public void testSortAccountLog() throws IOException {
        Banking banking = new Banking();
        AccountLog accountLog = banking.getAccountActivityLog();
        
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
        
        banking.saveAccountLog("account-log.xml");
        
        Banking banking2 = new Banking();
        
        banking2.retrieveAccountLog("account-log.xml");
        
        for (AccountActivity record : banking2.getAccountActivityLog().getActivityRecords()) {
            System.out.println(record);
        }
        
        
    }

}
