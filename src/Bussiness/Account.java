package Bussiness;

import java.util.ArrayList;

public class Account {
	double amount;
	String accountNo;
	ArrayList<Transaction> transactions=new ArrayList<Transaction>();

	public Account(String accountNo) {
		super();
		this.accountNo = accountNo;
	}

	public double getAmount() {
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

	public long showInquiry() {
		long total=0;
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
