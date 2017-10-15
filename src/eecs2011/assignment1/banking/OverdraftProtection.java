package eecs2011.assignment1.banking;

import java.io.Serializable;

public enum OverdraftProtection implements Serializable {

	NONE,
	PAY_PER_USE,
	MONTHLY_FIXED_FEE
}
