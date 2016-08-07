package Bussiness;

import java.util.ArrayList;
import java.util.Date;

public class Account {
	double amount;
	String accountNo;
	Date createdDate;
	ArrayList<Transaction> transactions=new ArrayList<Transaction>();

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Account(String accountNo) {
		super();
		this.accountNo = accountNo;
	}

	public Account(double amount, String accountNo,Date created) {
		super();
		this.amount = amount;
		this.accountNo = accountNo;
		this.createdDate=created;
	}

	public double getAmount() {
		this.amount=0;
		for (Transaction tr : transactions){
			if(tr.getFromAccount().equals(getAccountNo())){
				this.amount=this.amount+(tr.getAmount()*-1);
			}
			else{
				this.amount=this.amount+tr.getAmount();
			}
		}
		
		return amount;
	}

	public void credit(double amount) {
		this.amount = this.amount+amount;
	}
	public void debit(double amount) {
		this.amount = this.amount-amount;
	}

	public void printHistory() {
		for (Transaction tr : transactions){
			System.out.println(""+tr.getTransactionDate()+" From "+tr.getFromAccount()+" To "+tr.getToAccount()+" Amount "+tr.amount);
		}
		
	}

	public double showInquiry() {
		double total=0;
		for (Transaction tr : transactions){
			if(this.accountNo.equals(tr.getFromAccount())){
				total=total+(-1)*tr.amount;
			}
			else{
				total=total+tr.amount;
			}
		}
		return total;
	}	
}
