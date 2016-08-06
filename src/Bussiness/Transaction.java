package Bussiness;

import java.util.Date;

public class Transaction {
		long amount;
		Account toAccount;
		Account fromAccount;
		Date transactionDate;
	public double getAmount() {
		return amount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}

}
