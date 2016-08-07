package Bussiness;

import java.sql.SQLException;
import java.util.Date;

import javax.naming.InsufficientResourcesException;

import Exceptions.AccountExsists;
import Exceptions.InsufficientBalance;

public class AccountHandler {

	public static boolean CreateAccount(User byuser,String accountNo) throws SQLException, AccountExsists{
		//if()
		if(DBLink.isAccountExsist(accountNo)){
			
			throw new AccountExsists(accountNo, byuser.getUname());
		}
		DBLink.createAccount(accountNo, byuser.getId());
		
		return true;
	}
	
	public static boolean TransferAmount(Account from,Account to,double amount,User byuser) throws SQLException, InsufficientBalance{
		
		if(from.getAmount()>amount){
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
			throw new InsufficientBalance(byuser.getUname(),from.getAccountNo(),to.getAccountNo(),amount);
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
			boolean res=DBLink.submitTransaction(tr_temp, byuser);
			from.getTransactions().add(tr_temp);
			
			
			return res;
		}
		else{
			System.out.println("ELSE"+from.getAccountNo());
			return false;
		}
		
		
	}
	
	public static void UpdateTransactionSet(Account account) throws SQLException{
		account.setTransactions(DBLink.getTransactions(account.getAccountNo()));
	}
	
	
}
