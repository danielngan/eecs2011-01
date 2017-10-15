package eecs2011.assignment1.banking;

import java.io.Serializable;

public enum AccountActivityType implements Serializable {
    
    WITHDRAW_AMOUNT,
    DEPOSIT_AMOUNT,
    CREATE_ACCOUNT,
    CANCEL_ACCOUNT,
    SUSPEND_ACCOUNT,
    REACTIVATE_ACCOUNT,
    GET_BALANCE,
    TERMINATE_ACCOUNT,
    SET_OVERDRAFT_OPTION,
    SET_LIMIT,
    TRANSFER_AMOUNT_TO,
    TRANSFER_AMOUNT_FROM
}
