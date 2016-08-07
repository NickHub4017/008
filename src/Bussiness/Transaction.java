package Bussiness;

import java.util.Date;

public class Transaction {
		double amount;
		String toAccount;
		String fromAccount;
		Date transactionDate;
	
		
	public Transaction(double amount, String fromAccount,String toAccount, Date date) {
		this.amount=amount;
		this.toAccount=toAccount;
		this.fromAccount=fromAccount;
		this.transactionDate=date;
		}
	public double getAmount() {
		return amount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}

}
