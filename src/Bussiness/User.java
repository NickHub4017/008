package Bussiness;

import java.sql.SQLException;
import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;

public class User {
	 String Uname;
	 String Pwd;
	 int id;//This will get 0< value when login successful
	 HashMap<String, Account> accounts=new HashMap<String,Account>();
	 public HashMap<String, Account> getAccounts() {
		return accounts;
	}



	public void setAccounts(HashMap<String, Account> accounts) {
		this.accounts = accounts;
	}



	String Name;
	 String address;
	
	public User(String uname, String pwd) {
		super();
		Uname = uname;
		Pwd = pwd;
	}



	public String getUname() {
		return Uname;
	}



	public int getId() {
		return id;
	}


	
	
	
	public boolean createAccount(String accountNo) throws SQLException{
		boolean res= AccountHandler.CreateAccount(this, accountNo);
		if(res){
			Account newAcc=new Account(accountNo);
			accounts.put(accountNo, newAcc);
		}
		return res;
	}
	
	public boolean TransferAmount(String from_accountNo,String to_accountNo,long amount) throws SQLException{
		Account toaccount=new Account(to_accountNo);
		return AccountHandler.TransferAmount(accounts.get(from_accountNo), toaccount, amount,this);
	}



	public void showHsitory(String accountNo) {
		Account account=accounts.get(accountNo);
		account.printHistory();
		
		
	}



	public void InquireAccount(String accountNo) {
		Account account=accounts.get(accountNo);
		System.out.println(account.showInquiry());
	}



	public void changeName(String newname) {
		this.Name=newname;
		
	}



	public void changeAddress(String newaddr) {
		this.address=newaddr;
		
	}



	public void putMessage(String msg) throws SQLException {
		MessageHandler.putMessage(msg,this);
		
	}
	
	public boolean deposit(String to,double amount) throws SQLException{
		Account toacc=new Account(to);
		if(accounts.containsKey(to)){
			toacc=accounts.get(to);
		}
		
		 AccountHandler.Deposit(toacc, this, amount);
		 return true;
	}
	
	public boolean withdraw(String from,double amount) throws SQLException{
		Account fromacc=new Account(from);
		 return AccountHandler.Withdraw(fromacc, this, amount);
		 
	}
}
