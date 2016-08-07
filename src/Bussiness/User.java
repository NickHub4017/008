package Bussiness;

import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InsufficientResourcesException;
import javax.print.attribute.HashAttributeSet;

import Exceptions.AccountExsists;
import Exceptions.InsufficientBalance;

public class User {
	 String Uname;
	 String Pwd;
	 int id;//This will get 0< value when login successful
	 HashMap<String, Account> accounts=new HashMap<String,Account>();
	 Profile profile;
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


	
	
	
	public boolean createAccount(String accountNo) throws SQLException, AccountExsists{
		boolean res= AccountHandler.CreateAccount(this, accountNo);
		if(res){
			Account newAcc=new Account(accountNo);
			accounts.put(accountNo, newAcc);
		}
		return res;
	}
	
	public boolean TransferAmount(String from_accountNo,String to_accountNo,long amount) throws SQLException, InsufficientBalance{
		Account toaccount=new Account(to_accountNo);
		return AccountHandler.TransferAmount(accounts.get(from_accountNo), toaccount, amount,this);
	}



	public void showHsitory(String accountNo) throws SQLException {
		Account account=accounts.get(accountNo);
		AccountHandler.UpdateTransactionSet(account);//Update Account transaction List
		account.printHistory();
		
		
	}



	public void InquireAccount(String accountNo) throws SQLException {
		
		Account account=accounts.get(accountNo);
		AccountHandler.UpdateTransactionSet(account);
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
	
	public Profile getProfile() {
		return profile;
	}



	public void setProfile(Profile profile) {
		this.profile = profile;
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
		if(accounts.containsKey(from)){
			fromacc=accounts.get(from);
		}
		 return AccountHandler.Withdraw(fromacc, this, amount);
		 
	}
	
	
}
