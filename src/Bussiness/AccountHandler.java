package Bussiness;

import java.sql.SQLException;
import java.util.Date;

public class AccountHandler {

	public static boolean CreateAccount(User byuser,String accountNo) throws SQLException{
		//if()
		if(DBLink.isAccountExsist()){
			return false;
		}
		DBLink.createAccount(accountNo, byuser.getId());
		
		return true;
	}
	
	public static boolean TransferAmount(Account from,Account to,long amount,User byuser) throws SQLException{
		if(from.getAmount()<amount){
			from.debit(amount);
			to.credit(amount);
			Transaction tr_temp=new Transaction(amount, from.getAccountNo(), to.getAccountNo(), new Date());
			if(DBLink.submitTransaction(tr_temp,byuser)){
				from.getTransactions().add(tr_temp);
				to.getTransactions().add(tr_temp);
				return true;
			}
			else{
				return false;
			}
		}
		else{
			System.out.println("Insufficient Amount : Your balance is "+from.getAmount());
			return false;
		}
	}
	public static String ShowHistory(Account from){
		//DBLink.getHistory();
		return "";
	}

	public static String Getnquiry(Account from){
		//DBLink.getInquiry();
		return "";
	}
	
	public static boolean Deposit(Account to,User byuser,double amount) throws SQLException{
		Transaction tr_temp=new Transaction(amount, "Cachier",to.accountNo, new Date());
		DBLink.submitTransaction(tr_temp, byuser);
		to.getTransactions().add(tr_temp);
		return true;
	}
	
	public static boolean Withdraw(Account from,User byuser,double amount) throws SQLException{
		if(from.getAmount()>=amount){
			Transaction tr_temp=new Transaction(amount, from.accountNo,"ATM", new Date());
			DBLink.submitTransaction(tr_temp, byuser);
			from.getTransactions().add(tr_temp);
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	
}
